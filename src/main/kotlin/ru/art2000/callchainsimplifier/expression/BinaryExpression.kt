package ru.art2000.callchainsimplifier.expression

import ru.art2000.callchainsimplifier.Element
import ru.art2000.callchainsimplifier.Value
import kotlin.reflect.full.isSubclassOf

class BinaryExpression(operand1: Expression, val sign: BinarySign, operand2: Expression) : Expression() {

    val operand1: Expression = operand1.eval()

    val operand2: Expression = operand2.eval()

    init {
        if (this.operand1.returnType != this.operand2.returnType || this.operand1.returnType != sign.operands) {
            throw Exception("Invalid types: ${operand1.returnType} ${sign.operands} ${operand2.returnType}")
        }
    }

    override fun plus(e: Expression): Expression {
        return BinaryExpression(
            this,
            BinarySign.PLUS,
            e
        ).eval()
    }

    override fun minus(e: Expression): Expression {
        return BinaryExpression(
            this,
            BinarySign.MINUS,
            e
        ).eval()
    }

    override fun times(e: Expression): Expression {
        return BinaryExpression(
            this,
            BinarySign.MULTIPLY,
            e
        ).eval()
    }

    override fun unaryMinus(): Expression {
        return BinaryExpression(
            -operand1,
            sign,
            -operand2
        ).eval()
    }

    override fun doEval(): Expression {
        if (sign !in listOf(
                BinarySign.PLUS,
                BinarySign.MINUS,
                BinarySign.MULTIPLY
            )
        ) {
            return logicEval()
        }

        val e1 = operand1
        val e2 = operand2

        if (e2 == constantZero) {
            return if (sign == BinarySign.MULTIPLY) constantZero else e1
        }

        if ((!e1::class.isSubclassOf(ElementExpression::class) || !e2::class.isSubclassOf(ElementExpression::class))
            && e1 != constantZero
        ) {
            return this
        }

        return when (sign) {
            BinarySign.MULTIPLY -> e1 * e2
            BinarySign.PLUS -> e1 + e2
            BinarySign.MINUS -> e1 - e2
            else -> throw Exception("Never reached")
        }
    }

    internal fun logicEval(): Expression {
        //if we are here, then operand1 && operand2 - binary expressions


        if (sign == BinarySign.GREATER || sign == BinarySign.LESS) {
            val simplified = (operand1 - operand2).eval()
            if (simplified is BinaryExpression) {
                return BinaryExpression(
                    simplified.operand1,
                    sign,
                    -simplified.operand2
                )
            }
            if (simplified::class.isSubclassOf(ElementExpression::class)) {
                simplified as ElementExpression
                when {
                    simplified.count == 0 -> {
                        return if (simplified < constantZero && sign == BinarySign.LESS || simplified > constantZero && sign == BinarySign.GREATER)
                            Element.TRUE_FILTER
                        else
                            Element.FALSE_FILTER
                    }
                    simplified < constantZero -> return BinaryExpression(
                        -simplified,
                        if (sign == BinarySign.GREATER) BinarySign.LESS else BinarySign.GREATER,
                        constantZero
                    )

                }
            }
            return BinaryExpression(
                simplified,
                sign,
                constantZero
            )
        }

        if (sign == BinarySign.EQUAL) {
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
                    if (simplified is BinaryExpression) {
                        return BinaryExpression(
                            simplified.operand1,
                            sign,
                            -simplified.operand2
                        )
                    }
                    if (simplified::class.isSubclassOf(ElementExpression::class) && (simplified as ElementExpression) < constantZero) {
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

        operand1 as BinaryExpression
        operand2 as BinaryExpression

        if (sign == BinarySign.AND) {
            if (operand1 == Element.FALSE_FILTER || operand2 == Element.FALSE_FILTER)
                return Element.FALSE_FILTER


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
                if (operand1.operand1 == operand2.operand1 && operand1.operand2 == operand2.operand2) {
                    return Element.FALSE_FILTER
                }
            }
        } else if (sign == BinarySign.OR) {
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
        return "(" + operand1.toString() + sign.text + operand2.toString() + ")"
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
}