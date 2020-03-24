import grammar.LexerLexer
import grammar.LexerParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.antlr.v4.runtime.tree.TerminalNode

fun main() {

    val lexer = LexerLexer(CharStreams.fromString(readLine()))
    val tokens = CommonTokenStream(lexer as TokenSource)
    val parser = LexerParser(tokens as TokenStream)
    val tree = parser.callChain()

    val listener = GrammarListener()

    val walker = ParseTreeWalker()
    walker.walk(listener, tree)

    if (listener.hasErrors) {
        println("SYNTAX ERROR")
        return
    }

    listener.calls.forEach {
        if (it.childCount > 0) {
            when (val child = it.children[0]) {
                is LexerParser.MapCallContext -> {
                    try {
                        evaluateExpression(child.expression())
                    } catch (e: Exception) {
                        println("TYPE ERROR")
                        return
                    }
                }
                is LexerParser.FilterCallContext -> {
                    try {
                        evaluateExpression(child.expression())
                    } catch (e: Exception) {
                        println("TYPE ERROR")
                        return
                    }
                }
            }
        }
    }

    println(Element)
}

fun evaluateExpression(expressionContext: LexerParser.ExpressionContext): Expression {
    when {
        expressionContext.text == "element" -> return ElementExpression(Element.map)
        expressionContext.constantExpression() != null -> {
            return ConstantExpression(expressionContext.constantExpression())
        }
        expressionContext.binaryExpression() != null -> {
            val binaryExpr = expressionContext.binaryExpression()
            return applyBinaryOperation(binaryExpr.expression(0), binaryExpr.OPERATION(), binaryExpr.expression(1))
        }
        else -> {
            throw Exception("Unknown expression")
        }
    }
}

fun applyBinaryOperation(
    expr: LexerParser.ExpressionContext,
    signNode: TerminalNode,
    expr2: LexerParser.ExpressionContext
): Expression {

    val sign = BinaryExpression.Sign.values().find { it.text == signNode.text } ?: throw Exception("Unknown sign")

    val result = BinaryExpression(evaluateExpression(expr), sign, evaluateExpression(expr2))

    when (sign.returnType) {
        Value.Type.INT -> Element.applyTransformation(result)
        Value.Type.BOOL -> Element.applyFilter(result)
    }
    return result
}