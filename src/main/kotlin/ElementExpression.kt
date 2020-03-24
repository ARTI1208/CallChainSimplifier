class ElementExpression(val value: String): Expression() {

    override fun eval(): String {
        return value
    }

    override val returnType: Value.Type
        get() = Value.Type.INT

    override fun toString(): String {
        return value
    }
}