package ru.art2000.callchainsimplifier

import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.antlr.v4.runtime.tree.TerminalNode
import ru.art2000.callchainsimplifier.expression.*
import ru.art2000.callchainsimplifier.grammar.GrammarErrorHandler
import ru.art2000.callchainsimplifier.grammar.GrammarListener
import ru.art2000.callchainsimplifier.grammar.LexerLexer
import ru.art2000.callchainsimplifier.grammar.LexerParser

class Simplifier {

    companion object {
        val TRUE_FILTER = BinaryExpression(
            constantOne,
            BinarySign.GREATER,
            constantZero
        )
        val FALSE_FILTER = BinaryExpression(
            constantOne,
            BinarySign.LESS,
            constantZero
        )

        val MAP_DEFAULT = ElementExpression(1, 1, 0)
    }

    private val callToParam = mapOf(
        LexerParser.MapCallContext::class to listOf(ValueType.INT),
        LexerParser.FilterCallContext::class to listOf(ValueType.BOOL),
        LexerParser.BinaryExpressionContext::class to listOf(
            ValueType.INT,
            ValueType.BOOL
        )
    )

    private var filter: Expression = TRUE_FILTER

    private var map: Expression = MAP_DEFAULT

    private fun applyFilter(f: Expression) {
        require(f.returnType == ValueType.BOOL)

        filter = when (filter) {
            TRUE_FILTER -> f
            FALSE_FILTER -> FALSE_FILTER
            else -> BinaryExpression(
                filter,
                BinarySign.AND,
                f
            ).logicEval()
        }
    }

    private fun applyTransformation(t: Expression) {
        require(t.returnType == ValueType.INT)
        map = if (filter == FALSE_FILTER) MAP_DEFAULT else t
    }

    override fun toString(): String {
        return "filter{$filter}%>%map{${map}}"
    }

    fun simplify(input: String): String {

        map = MAP_DEFAULT
        filter = TRUE_FILTER

        val lexer = LexerLexer(CharStreams.fromString(input))
        val tokens = CommonTokenStream(lexer as TokenSource)
        val parser = LexerParser(tokens as TokenStream)
        parser.errorHandler = GrammarErrorHandler()

        val listener = GrammarListener()

        try {
            val tree = parser.callChain()
            val walker = ParseTreeWalker()
            walker.walk(listener, tree)
        } catch (e: Exception) {
            return "SYNTAX ERROR"
        }

        if (listener.hasErrors) {
            return "SYNTAX ERROR"
        }

        listener.calls.forEach {
            if (it.childCount > 0) {
                when (val child = it.children[0]) {
                    is LexerParser.MapCallContext -> {
                        try {
                            val res =
                                evaluateExpression(child.expression())
                            applyTransformation(res)
                        } catch (e: Exception) {
                            return "TYPE ERROR"
                        }
                    }
                    is LexerParser.FilterCallContext -> {
                        try {
                            val res =
                                evaluateExpression(child.expression())
                            applyFilter(res)
                        } catch (e: Exception) {
                            return "TYPE ERROR"
                        }
                    }
                }
            }
        }

        return toString()
    }

    private tailrec fun checkHasParentOfType(child: RuleContext, type: ValueType): Boolean {
        if (child.parent == null)
            return false

        val entry = callToParam.entries.find { it.key == child.parent::class }

        if (entry != null && type in entry.value) {
            return true
        }

        return checkHasParentOfType(child.parent, type)
    }

    private fun evaluateExpression(expressionContext: LexerParser.ExpressionContext): Expression {
        when {
            expressionContext.text == "element" -> {
                require(
                    checkHasParentOfType(
                        expressionContext,
                        ValueType.INT
                    )
                )

                return map
            }
            expressionContext.constantExpression() != null -> {
                require(
                    checkHasParentOfType(
                        expressionContext,
                        ValueType.INT
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

    private fun applyBinaryOperation(
        expr: LexerParser.ExpressionContext,
        signNode: TerminalNode,
        expr2: LexerParser.ExpressionContext
    ): Expression {

        val sign = BinarySign.values().find { it.text == signNode.text } ?: throw Exception("Unknown sign")
        return BinaryExpression(
            evaluateExpression(expr),
            sign,
            evaluateExpression(expr2)
        ).eval()
    }
}