package ru.art2000.callchainsimplifier.expression

import ru.art2000.callchainsimplifier.Value
import kotlin.math.absoluteValue
import kotlin.reflect.full.isSubclassOf

open class ElementExpression(count: Int = 1, power: Int = 1, real: Int = 0) : Expression() {

    var count: Int = 1
        private set

    var power: Int = 1
        private set

    var real: Int = 0
        private set

    val index: Int

    init {
        this.count = count
        this.power = power
        this.real = real
        if (count == 0)
            this.power = 0

        @Suppress("UNNECESSARY_SAFE_CALL", "USELESS_ELVIS")
//        index = ru.art2000.callchainsimplifier.Element.transformations?.size?.minus(1) ?: -1
        index = 0
    }

    override fun doEval(): Expression {
        return this
    }

    override val returnType: Value.Type
        get() = Value.Type.INT

    override fun toString(): String {

        if (count == 0) {
            return real.toString()
        }

//        val default = if (index >= 0) {
//            if (ru.art2000.callchainsimplifier.Element.transformations[index]::class.isSubclassOf(this::class)) "element" else ru.art2000.callchainsimplifier.Element.transformations[index].toString()
//        } else
//            "element"

        val default = "element"

        val imaginary = when {
            count == 1 && power == 1 -> default
            power == 1 -> "($count*$default)"
            else -> {
                var builder = default

                repeat(power - 1) {
                    builder = "($builder*$default)"
                }

                if (count > 1)
                    "($count*$builder)"
                else
                    builder
            }
        }

        return if (real == 0) imaginary else {
            if (real < 0) {
                "($imaginary-${real.absoluteValue})"
            } else {
                "($imaginary+${real.absoluteValue})"
            }
        }
    }


    override fun plus(e: Expression): Expression {
        return when (e) {
            is ElementExpression -> plus(e)
            is BinaryExpression -> BinaryExpression(
                this,
                BinaryExpression.Sign.PLUS,
                e
            ).eval()
            else -> throw Exception("Unknown expr")
        }
    }

    override fun minus(e: Expression): Expression {
        return when (e) {
            is ElementExpression -> minus(e)
            is BinaryExpression -> BinaryExpression(
                this,
                BinaryExpression.Sign.MINUS,
                e
            ).eval()
            else -> throw Exception("Unknown expr")
        }
    }

    override fun times(e: Expression): Expression {
        return when (e) {
            is ElementExpression -> times(e)
            is BinaryExpression -> BinaryExpression(
                this,
                BinaryExpression.Sign.MULTIPLY,
                e
            ).eval()
            else -> throw Exception("Unknown expr")
        }
    }

    override fun unaryMinus(): ElementExpression {
        return ElementExpression(-count, power, -real)
    }

    operator fun plus(element: ElementExpression): Expression {
        if (count * element.count == 0) {
            val thisFactor = if (count == 0) 0 else 1
            val elementFactor = if (element.count == 0) 0 else 1

            return ElementExpression(
                count + element.count,
                thisFactor * power + elementFactor * element.power,
                real + element.real
            )
        }

        if (power != element.power) {
            return BinaryExpression(
                this,
                BinaryExpression.Sign.PLUS,
                element
            )
        }

        return ElementExpression(
            count + element.count,
            power,
            real + element.real
        )
    }

    operator fun minus(element: ElementExpression): Expression {
        println("t: $this --- $element")
        if (count * element.count == 0) {
            val thisFactor = if (count == 0) 0 else 1
            val elementFactor = if (element.count == 0) 0 else 1

            return ElementExpression(
                count - element.count,
                thisFactor * power + elementFactor * element.power,
                real - element.real
            )
        }

        return if (power == element.power)
            ElementExpression(
                count - element.count,
                element.power,
                real - element.real
            )
        else {
            println("kk")
            (BinaryExpression(
                ElementExpression(count, power, 0),
                BinaryExpression.Sign.MINUS,
                ElementExpression(
                    element.count,
                    element.power,
                    0
                )
            ).also { it.isEvaluable = false } + ConstantExpression(
                real - element.real
            )).eval()
        }
    }

    operator fun times(element: ElementExpression): Expression {

        var secondTerm = ElementExpression(
            real * element.count,
            element.power,
            0
        )

        var thirdTerm = ElementExpression(
            element.real * count,
            power,
            0
        )

        if (power > element.power)
            secondTerm = thirdTerm.also { thirdTerm = secondTerm }


        return BinaryExpression(
            ElementExpression(
                count * element.count,
                power + element.power,
                real * element.real
            ),
            BinaryExpression.Sign.PLUS,
            BinaryExpression(
                secondTerm,
                BinaryExpression.Sign.PLUS,
                thirdTerm
            ).eval()
        ).eval()
    }

    //TODO: Treating array element as positive number - is it valid?
    operator fun compareTo(element: ElementExpression): Int {
        if (count * element.count != 0 && power != element.power) return power - element.power
        if (count != element.count) return count - element.count
        if (real != element.real) return real - element.real

        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other?: return false
        if (!other::class.isSubclassOf(this::class) && !this::class.isSubclassOf(other::class)) return false

        other as ElementExpression

        if (count != other.count) return false
        if (power != other.power) return false
        if (real != other.real) return false


        return true
    }

    override fun hashCode(): Int {
        var result = count
        result = 31 * result + power
        result = 31 * result + real
        return result
    }
}