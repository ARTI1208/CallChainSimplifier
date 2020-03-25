import kotlin.reflect.full.isSubclassOf

class BinaryExpression(val operand1: Expression, val sign: Sign, val operand2: Expression) : Expression() {

    init {
        if (operand1.returnType != operand2.returnType || operand1.returnType != sign.operands) {
            throw Exception("Invalid types: ${operand1.returnType} ${sign.operands} ${operand2.returnType}")
        }
    }

    override fun plus(e: Expression): Expression {
        val other = e.eval()
        return BinaryExpression(this, Sign.PLUS, other).eval()
    }

    override fun minus(e: Expression): Expression {
        val other = e.eval()
        return BinaryExpression(this.eval(), Sign.MINUS, other).eval()
    }

    override fun times(e: Expression): Expression {
        return BinaryExpression(this.eval(), Sign.MULTIPLY, e).eval()
    }

    override fun eval(): Expression {
        if (sign !in listOf(Sign.PLUS, Sign.MINUS, Sign.MULTIPLY)) {
            return this
        }

        val e1 = if (operand1 is BinaryExpression) operand1.eval() else operand1
        val e2 = if (operand2 is BinaryExpression) operand2.eval() else operand2

        if (!e1::class.isSubclassOf(ElementExpression::class) || !e2::class.isSubclassOf(ElementExpression::class)) {
            return this
        }

        return when (sign) {
            Sign.MULTIPLY -> e1 * e2
            Sign.PLUS -> e1 + e2
            Sign.MINUS -> e1 - e2
            else -> throw Exception("Never reached")
        }
    }

    override fun toString(): String {
        return "(" + operand1.eval().toString() + sign.text + operand2.eval().toString() + ")"
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