package ru.art2000.callchainsimplifier.expression

enum class BinarySign(val text: String, val operands: ValueType, val returnType: ValueType) {
    PLUS("+", ValueType.INT, ValueType.INT),
    MINUS("-", ValueType.INT, ValueType.INT),
    MULTIPLY("*", ValueType.INT, ValueType.INT),
    GREATER(">", ValueType.INT, ValueType.BOOL),
    LESS("<", ValueType.INT, ValueType.BOOL),
    EQUAL("=", ValueType.INT, ValueType.BOOL),
    AND("&", ValueType.BOOL, ValueType.BOOL),
    OR("|", ValueType.BOOL, ValueType.BOOL), ;

    override fun toString(): String {
        return text
    }
}