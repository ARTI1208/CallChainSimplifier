class BinaryExpression(val operand1: Expression, val sign: Sign, val operand2: Expression) : Expression() {

    init {
        if (operand1.returnType != operand2.returnType || operand1.returnType != sign.operands) {
            throw Exception("Invalid types")
        }
    }

    override fun eval(): Any {
        return toString()
    }

    override fun toString(): String {
        return operand1.eval().toString() + sign.text + operand2.eval().toString()
    }

    fun isValid(): Boolean {
        if (returnType == Value.Type.INT)
            return true

        return true
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
        AND("&", Value.Type.INT, Value.Type.BOOL),
        OR("|", Value.Type.INT, Value.Type.BOOL),;

        override fun toString(): String {
            return text
        }
    }
}