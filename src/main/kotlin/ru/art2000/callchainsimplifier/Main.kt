package ru.art2000.callchainsimplifier

import ru.art2000.callchainsimplifier.grammar.LexerLexer
import ru.art2000.callchainsimplifier.grammar.LexerParser
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.antlr.v4.runtime.tree.TerminalNode
import ru.art2000.callchainsimplifier.expression.BinaryExpression
import ru.art2000.callchainsimplifier.expression.ElementExpression
import ru.art2000.callchainsimplifier.expression.Expression

fun main() {

    val lexer = LexerLexer(CharStreams.fromString(readLine()))
    val tokens = CommonTokenStream(lexer as TokenSource)
    val parser = LexerParser(tokens as TokenStream)
    parser.errorHandler = GrammarErrorHandler()

    val listener = GrammarListener()

    try {
        val tree = parser.callChain()
        val walker = ParseTreeWalker()
        walker.walk(listener, tree)
    } catch (e: Exception) {
        println("SYNTAX ERROR")
        return
    }

    if (listener.hasErrors) {
        println("SYNTAX ERROR")
        return
    }

    listener.calls.forEach {
        if (it.childCount > 0) {
            when (val child = it.children[0]) {
                is LexerParser.MapCallContext -> {
                    try {
                        val res =
                            evaluateExpression(child.expression())
                        Element.applyTransformation(res)
                    } catch (e: Exception) {
                        println("TYPE ERROR")
                        return
                    }
                }
                is LexerParser.FilterCallContext -> {
                    try {
                        val res =
                            evaluateExpression(child.expression())
                        Element.applyFilter(res)
                    } catch (e: Exception) {
                        println("TYPE ERROR")
                        return
                    }
                }
            }
        }
    }

    println(Element)
    println(Element.filter == Element.FALSE_FILTER)
}

val callToParam = mapOf(
    LexerParser.MapCallContext::class to listOf(Value.Type.INT),
    LexerParser.FilterCallContext::class to listOf(Value.Type.BOOL),
    LexerParser.BinaryExpressionContext::class to listOf(
        Value.Type.INT,
        Value.Type.BOOL
    )
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
            require(
                checkHasParentOfType(
                    expressionContext,
                    Value.Type.INT
                )
            )

            return Element.transformations.last()
        }
        expressionContext.constantExpression() != null -> {
            require(
                checkHasParentOfType(
                    expressionContext,
                    Value.Type.INT
                )
            )

            return ElementExpression(
                0,
                0,
                expressionContext.constantExpression().text.toInt()
            )
        }
        expressionContext.binaryExpression() != null -> {

            val binaryExpr = expressionContext.binaryExpression()

            val binaryExpression =
                applyBinaryOperation(
                    binaryExpr.expression(0),
                    binaryExpr.OPERATION(),
                    binaryExpr.expression(1)
                )
            require(
                checkHasParentOfType(
                    binaryExpr,
                    binaryExpression.returnType
                )
            )
            return binaryExpression
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
    return BinaryExpression(
        evaluateExpression(expr),
        sign,
        evaluateExpression(expr2)
    ).eval()
}