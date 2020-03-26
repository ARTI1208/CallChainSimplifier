package ru.art2000.callchainsimplifier.expression

import ru.art2000.callchainsimplifier.Element
import ru.art2000.callchainsimplifier.Value
import kotlin.reflect.full.isSubclassOf

class BinaryExpression(val operand1: Expression, val sign: Sign, val operand2: Expression) : Expression() {

    init {
        if (operand1.returnType != operand2.returnType || operand1.returnType != sign.operands) {
            throw Exception("Invalid types: ${operand1.returnType} ${sign.operands} ${operand2.returnType}")
        }
    }

    override fun plus(e: Expression): Expression {
        return BinaryExpression(
            this,
            Sign.PLUS,
            e
        ).eval()
    }

    override fun minus(e: Expression): Expression {
        return BinaryExpression(
            this,
            Sign.MINUS,
            e
        ).eval()
    }

    override fun times(e: Expression): Expression {
        return BinaryExpression(
            this,
            Sign.MULTIPLY,
            e
        ).eval()
    }

    override fun unaryMinus(): Expression {
        return BinaryExpression(
            -constantOne,
            Sign.MULTIPLY,
            this
        ).eval()
    }

    override fun doEval(): Expression {
        if (sign !in listOf(
                Sign.PLUS,
                Sign.MINUS,
                Sign.MULTIPLY
            )) {
            return logicEval()
        }

        val e1 = if (operand1 is BinaryExpression) operand1.eval() else operand1
        val e2 = if (operand2 is BinaryExpression) operand2.eval() else operand2

        if (e1::class.isSubclassOf(ElementExpression::class) && (e1 as ElementExpression).let { it.count == 0 && it.real == 0 })
            return e2

        if (e2::class.isSubclassOf(ElementExpression::class) && (e2 as ElementExpression).let { it.count == 0 && it.real == 0 })
            return e1

        if (!e1::class.isSubclassOf(ElementExpression::class) || !e2::class.isSubclassOf(
                ElementExpression::class)) {
            return this
        }

        return when (sign) {
            Sign.MULTIPLY -> e1 * e2
            Sign.PLUS -> e1 + e2
            Sign.MINUS -> e1 - e2
            else -> throw Exception("Never reached")
        }
    }

    private fun logicEval(): Expression {
        //if we are here, then operand1 && operand2 - binary expressions


        if (sign == Sign.GREATER || sign == Sign.LESS) {
            val simplified = (operand1 - operand2).eval()
            if (simplified is BinaryExpression) {
                println("tt")
                return BinaryExpression(
                    simplified.operand1,
                    sign,
                    -simplified.operand2
                )
            }
            if (simplified::class.isSubclassOf(ElementExpression::class)) {
                simplified as ElementExpression
                println("rrr")
                if (simplified < constantZero)
                    return BinaryExpression(
                        -simplified,
                        sign,
                        constantZero
                    )
                else if (simplified == constantZero)
                    return Element.FALSE_FILTER
            }
            println("lll")
            return BinaryExpression(
                simplified,
                sign,
                constantZero
            )
        }

        if (sign == Sign.EQUAL) {
            operand1 as ElementExpression
            operand2 as ElementExpression
            when {
                (operand1.count == operand2.count && operand1.power == operand2.power) -> {
                    return if (operand1.real == operand2.real)
                        Element.TRUE_FILTER
                    else
                        Element.FALSE_FILTER
                }
                else -> {
                    val simplified = (operand1 - operand2).eval()
                    println("hi")
                    if (simplified is BinaryExpression) {
                        println("hi2")
                        return BinaryExpression(
                            simplified.operand1,
                            sign,
                            -simplified.operand2
                        )
                    }
                    if (simplified::class.isSubclassOf(ElementExpression::class) && (simplified as ElementExpression) < constantZero) {
                        println("hi3")
                        return BinaryExpression(
                            -simplified,
                            sign,
                            constantZero
                        )
                    }

                    return BinaryExpression(
                        simplified,
                        sign,
                        constantZero
                    )
                }
            }
        }

//        if (sign !in listOf(Sign.AND)) return this

//        println("logic1: $operand1")
//        println("logic1: ${operand1.javaClass}")
//        println("logic2: $operand2")
//        println("logic2: ${operand2.javaClass}")

        operand1 as BinaryExpression
        operand2 as BinaryExpression

        if (sign == Sign.AND) {
            operand1.operand1 as ElementExpression
            operand1.operand2 as ElementExpression
            operand2.operand1 as ElementExpression
            operand2.operand2 as ElementExpression
            if (operand1.sign == operand2.sign) {

                if (operand1.operand1 == operand2.operand1) {
                    return if (operand1.operand2 > operand2.operand2) operand1 else operand2
                }

                if (operand1.operand2 == operand2.operand2) {
                    return if (operand1.operand1 > operand2.operand1) operand1 else operand2
                }
            } else {
                if (operand1.operand1 == operand2.operand1 && operand1.operand2 == operand2.operand2)
                    return Element.FALSE_FILTER
            }
        } else if (sign == Sign.OR) {
            operand1.operand1 as ElementExpression
            operand1.operand2 as ElementExpression
            operand2.operand1 as ElementExpression
            operand2.operand2 as ElementExpression
            if (operand1.sign == operand2.sign) {

                if (operand1.operand1 == operand2.operand1) {
                    return if (operand1.operand2 > operand2.operand2) operand2 else operand1
                }

                if (operand1.operand2 == operand2.operand2) {
                    return if (operand1.operand1 > operand2.operand1) operand2 else operand1
                }
            } else {
                if (operand1.operand1 == operand2.operand1 && operand1.operand2 == operand2.operand2)
                    return Element.TRUE_FILTER
            }
        }

        return this
    }

    override fun toString(): String {
        return "(" + operand1.eval().toString() + sign.text + operand2.eval().toString() + ")"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BinaryExpression

        if (operand1 != other.operand1) return false
        if (sign != other.sign) return false
        if (operand2 != other.operand2) return false

        return true
    }

    override fun hashCode(): Int {
        var result = operand1.hashCode()
        result = 31 * result + sign.hashCode()
        result = 31 * result + operand2.hashCode()
        return result
    }

    override val returnType: Value.Type
        get() = sign.returnType

    enum class Sign(val text: String, val operands: Value.Type, val returnType: Value.Type) {
        PLUS("+", Value.Type.INT, Value.Type.INT),
        MINUS("-", Value.Type.INT, Value.Type.INT),
        MULTIPLY("*", Value.Type.INT, Value.Type.INT),
        GREATER(">", Value.Type.INT, Value.Type.BOOL),
        LESS("<", Value.Type.INT, Value.Type.BOOL),
        EQUAL("=", Value.Type.INT, Value.Type.BOOL),
        AND("&", Value.Type.BOOL, Value.Type.BOOL),
        OR("|", Value.Type.BOOL, Value.Type.BOOL), ;

        override fun toString(): String {
            return text
        }
    }
}