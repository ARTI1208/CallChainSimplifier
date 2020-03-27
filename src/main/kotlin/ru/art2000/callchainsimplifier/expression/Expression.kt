package ru.art2000.callchainsimplifier.expression

abstract class Expression {

    var isEvaluable: Boolean = true

    fun eval(): Expression = if (isEvaluable) doEval() else this

    abstract fun doEval(): Expression

    abstract val returnType: ValueType

    abstract operator fun plus(e: Expression): Expression

    abstract operator fun minus(e: Expression): Expression

    abstract operator fun times(e: Expression): Expression

    abstract operator fun unaryMinus(): Expression
}