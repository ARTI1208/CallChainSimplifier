import grammar.LexerParser
import java.lang.Exception

class ConstantExpression(val value: Int): Expression() {
    override fun eval(): ConstantExpression {
        return this
    }

    init {
        println("Const: " + value)
    }

    override val returnType: Value.Type
        get() = Value.Type.INT

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

    override fun toString(): String {
        return value.toString()
    }

    operator fun plus(constant: ConstantExpression): ConstantExpression {
        return ConstantExpression(value + constant.value)
    }

    operator fun minus(constant: ConstantExpression): ConstantExpression {
        return ConstantExpression(value - constant.value)
    }

    operator fun times(constant: ConstantExpression): ConstantExpression {
        return ConstantExpression(value * constant.value)
    }

    operator fun plus(element: ElementExpression): ElementExpression {
        return ElementExpression(element.value, element.count, element.power, value + element.real)
    }

    operator fun minus(element: ElementExpression): ElementExpression {
        return ElementExpression(element.value, element.count, element.power, value - element.real)
    }

    operator fun times(element: ElementExpression): ElementExpression {
        return ElementExpression(element.value, value * element.count, element.power, value * element.real)
    }
}