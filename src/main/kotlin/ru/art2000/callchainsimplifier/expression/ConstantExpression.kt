package ru.art2000.callchainsimplifier.expression

class ConstantExpression(real: Int) : ElementExpression(0, 0, real) {
    override fun toString(): String {
        return real.toString()
    }

    override fun unaryMinus(): ConstantExpression {
        return ConstantExpression(-real)
    }
}

val constantZero = ConstantExpression(0)
val constantOne = ConstantExpression(1)