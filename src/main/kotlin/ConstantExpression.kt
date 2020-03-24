import grammar.LexerParser

class ConstantExpression(val value: LexerParser.ConstantExpressionContext): Expression() {
    override fun eval(): Int {
        return value.text.toInt()
    }

    override val returnType: Value.Type
        get() = Value.Type.INT

    override fun toString(): String {
        return value.text
    }
}