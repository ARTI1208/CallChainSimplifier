import grammar.LexerLexer
import grammar.LexerParser
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.antlr.v4.runtime.tree.TerminalNode
import kotlin.reflect.KProperty1

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
                        println("TYPE ERROR: " + e.message)
                        return
                    }
                }
                is LexerParser.FilterCallContext -> {
                    try {
                        evaluateExpression(child.expression())
                    } catch (e: Exception) {
                        println("TYPE ERROR: " + e.message)
                        return
                    }
                }
            }
        }
    }

    println(Element)
}

val callToParam = mapOf(
    LexerParser.MapCallContext::class to listOf(Value.Type.INT),
    LexerParser.FilterCallContext::class to listOf(Value.Type.BOOL),
    LexerParser.BinaryExpressionContext::class to listOf(Value.Type.INT, Value.Type.BOOL)
)

tailrec fun checkHasParentOfType(child: RuleContext, type: Value.Type): Boolean {
    if (child.parent == null)
        return false

    val entry = callToParam.entries.find { it.key == child.parent::class }

    if (entry != null && type in entry.value) {
        return true
    }

    return checkHasParentOfType(child.parent, type)
}

fun evaluateExpression(expressionContext: LexerParser.ExpressionContext): Expression {
    when {
        expressionContext.text == "element" -> {
            require(checkHasParentOfType(expressionContext, Value.Type.INT))

            return ElementExpression(Element.map)
        }
        expressionContext.constantExpression() != null -> {
            require(checkHasParentOfType(expressionContext, Value.Type.INT))

            return ConstantExpression(expressionContext.constantExpression())
        }
        expressionContext.binaryExpression() != null -> {

            val binaryExpr = expressionContext.binaryExpression()

            val binaryExpression = applyBinaryOperation(binaryExpr.expression(0), binaryExpr.OPERATION(), binaryExpr.expression(1), binaryExpr)
            require(checkHasParentOfType(binaryExpr, binaryExpression.returnType))
            return binaryExpression
        }
        else -> {
            throw Exception("Unknown expression")
        }
    }
}

tailrec fun checkDirectChild(child: RuleContext): Boolean {
    if (child.parent == null || child.parent is LexerParser.BinaryExpressionContext)
        return false


    if (child.parent is LexerParser.CallContext) {
        return true
    }

    return checkDirectChild(child.parent)
}

fun applyBinaryOperation(
    expr: LexerParser.ExpressionContext,
    signNode: TerminalNode,
    expr2: LexerParser.ExpressionContext,
    binaryExpression: LexerParser.BinaryExpressionContext
): Expression {

    val sign = BinaryExpression.Sign.values().find { it.text == signNode.text } ?: throw Exception("Unknown sign")

    val result = BinaryExpression(evaluateExpression(expr), sign, evaluateExpression(expr2))

    if (checkDirectChild(binaryExpression)) {
        when (sign.returnType) {
            Value.Type.INT -> Element.applyTransformation(result)
            Value.Type.BOOL -> Element.applyFilter(result)
        }
    }

    return result
}