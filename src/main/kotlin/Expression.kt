abstract class Expression() {

    abstract fun eval(): Any

    abstract val returnType: Value.Type
}