abstract class Expression() {

    abstract fun eval(): Expression

    abstract val returnType: Value.Type

    abstract operator fun plus(e: Expression): Expression

    abstract operator fun minus(e: Expression): Expression

    abstract operator fun times(e: Expression): Expression

    object ZeroExpression: Expression() {
        override fun eval(): Expression {
            return this
        }

        override val returnType: Value.Type
            get() = Value.Type.INT

        override fun plus(e: Expression): Expression {
            return e
        }

        override fun minus(e: Expression): Expression {
            return e
        }

        override fun times(e: Expression): Expression {
            return this
        }

        override fun toString(): String {
            return "0"
        }
    }
}