abstract class Expression {

    abstract fun eval(): Expression

    abstract val returnType: Value.Type

    abstract operator fun plus(e: Expression): Expression

    abstract operator fun minus(e: Expression): Expression

    abstract operator fun times(e: Expression): Expression
}