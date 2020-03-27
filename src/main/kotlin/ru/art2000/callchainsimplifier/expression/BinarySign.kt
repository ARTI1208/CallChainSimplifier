package ru.art2000.callchainsimplifier.expression

import ru.art2000.callchainsimplifier.Value

enum class BinarySign(val text: String, val operands: Value.Type, val returnType: Value.Type) {
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