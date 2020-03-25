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

        index = Element.transformations.size - 1
    }

    override fun eval(): Expression {
        return this
    }

    override val returnType: Value.Type
        get() = Value.Type.INT

    override fun toString(): String {

        if (count == 0) {
            return real.toString()
        }

        val from = Element.transformations[index]

        val default = if (from::class.isSubclassOf(this::class)) "element" else from.toString()

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
            is BinaryExpression -> BinaryExpression(this, BinaryExpression.Sign.PLUS, e).eval()
            else -> throw Exception("Unknown expr")
        }
    }

    override fun minus(e: Expression): Expression {
        return when (e) {
            is ElementExpression -> minus(e)
            is BinaryExpression -> BinaryExpression(this, BinaryExpression.Sign.MINUS, e).eval()
            else -> throw Exception("Unknown expr")
        }
    }

    override fun times(e: Expression): Expression {
        return when (e) {
            is ElementExpression -> times(e)
            is BinaryExpression -> BinaryExpression(this, BinaryExpression.Sign.MULTIPLY, e).eval()
            else -> throw Exception("Unknown expr")
        }
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
            return BinaryExpression(this, BinaryExpression.Sign.PLUS, element)
        }

        return ElementExpression(count + element.count, power, real + element.real)
    }

    operator fun minus(element: ElementExpression): Expression {
        if (count * element.count == 0) {
            val thisFactor = if (count == 0) 0 else 1
            val elementFactor = if (element.count == 0) 0 else 1

            return ElementExpression(
                count + element.count,
                thisFactor * power + elementFactor * element.power,
                real - element.real
            )
        }

        return ElementExpression(count - element.count, element.power, real - element.real)
    }

    operator fun times(element: ElementExpression): Expression {
        return BinaryExpression(
            ElementExpression(count * element.count, power + element.power, real * element.real),
            BinaryExpression.Sign.PLUS,
            BinaryExpression(
                ElementExpression(
                    real * element.count,
                    element.power,
                    0
                ),
                BinaryExpression.Sign.PLUS,
                ElementExpression(element.real * count, power, 0)
            ).eval()
        ).eval()
    }
}