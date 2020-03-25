import java.lang.Exception
import kotlin.math.absoluteValue

class ElementExpression(val value: String, count: Int = 1 , power: Int = 1, real: Int = 0) : Expression() {

    var count: Int = 1
        private set

    var power: Int = 1
        private set

    var real: Int = 0
        private set

    lateinit var rootExpr: Expression
        private set

    init {
        init(Element.defaultMapExpression, count, power, real)
    }

    constructor(e: Expression, count: Int = 1, power: Int = 1, real: Int = 0) : this(e.toString(), count, power, real) {
        init(e, count, power, real)
    }

    private fun init(e: Expression, count: Int, power: Int, real: Int) {
        println("RootClass: ${if (e == null) "null" else e.javaClass}; value: $e")
        if (e is ElementExpression) {
            this.count = count * e.count
            this.power = power
            this.real = real
        } else {
            this.count = count
            this.power = power
            this.real = real
        }

        println("newElExpr: " + toString())

        rootExpr = e
    }

    override fun eval(): Expression {
        return this
    }

    override val returnType: Value.Type
        get() = Value.Type.INT

    override fun toString(): String {

        val imaginary =  when {
            count == 1 && power == 1 -> value
            power == 1 -> "($count*$value)"
            else -> {
                var builder = value

                println("Hello")

                repeat(power - 1) {
                    builder = "($builder*$value)"
                }

                if (count > 1)
                    "($count*$builder)"
                else
                    builder
            }
        }

        println("im: "+imaginary)

        return if (real == 0) imaginary else {
            if (real < 0) {
                "($imaginary-${real.absoluteValue})"
//                if (count != 1 || power != 1)
//                    "(($imaginary)-${real.absoluteValue})"
//                else
//
            } else {
                "($imaginary+${real.absoluteValue})"
            }
        }
    }

    override fun plus(e: Expression): Expression {
        return when (e) {
            is ConstantExpression -> plus(e)
            is ElementExpression -> plus(e)
            is BinaryExpression -> BinaryExpression(this, BinaryExpression.Sign.PLUS, e).eval()
            else -> throw Exception("Unknown expr")
        }
    }

    override fun minus(e: Expression): Expression {
        return when (e) {
            is ConstantExpression -> minus(e)
            is ElementExpression -> minus(e)
            is BinaryExpression -> BinaryExpression(this, BinaryExpression.Sign.MINUS, e).eval()
            else -> throw Exception("Unknown expr")
        }
    }

    override fun times(e: Expression): Expression {
        return when (e) {
            is ConstantExpression -> times(e)
            is ElementExpression -> times(e)
            is BinaryExpression -> BinaryExpression(this, BinaryExpression.Sign.MULTIPLY, e).eval()
            else -> throw Exception("Unknown expr")
        }
    }

    operator fun plus(constant: ConstantExpression): ElementExpression {
        return ElementExpression(rootExpr, count, power, real + constant.value)
    }

    operator fun minus(constant: ConstantExpression): ElementExpression {
        println("min2")
        val res = ElementExpression(rootExpr, count, power, real - constant.value)
        println(res.toString())
        return res
    }

    operator fun times(constant: ConstantExpression): ElementExpression {
        return ElementExpression(rootExpr, count * constant.value, power, real * constant.value)
    }

    operator fun plus(element: ElementExpression): Expression {
        if (power != element.power || value != element.value) {
            return BinaryExpression(this, BinaryExpression.Sign.PLUS, element)
        }

        return ElementExpression(rootExpr, count + element.count, element.power, real + element.real)
    }

    operator fun minus(element: ElementExpression): Expression {

        println("min")

        if (power != element.power || value != element.value) {
            return BinaryExpression(this, BinaryExpression.Sign.MINUS, element)
        }

        return ElementExpression(rootExpr, count - element.count, element.power, real - element.real)
    }

    operator fun times(element: ElementExpression): BinaryExpression {
        return BinaryExpression(
            ElementExpression(
                rootExpr,
                count * element.count,
                power + element.power,
                real * element.real
            ), BinaryExpression.Sign.PLUS,
            BinaryExpression(
                ElementExpression(rootExpr, real, element.count * power, 0),
                BinaryExpression.Sign.PLUS,
                ElementExpression(element.rootExpr, element.real, count * element.power, 0)
            )
        )
    }
}