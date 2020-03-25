class BinaryExpression(val operand1: Expression, val sign: Sign, val operand2: Expression) : Expression() {

    init {
        if (operand1.returnType != operand2.returnType || operand1.returnType != sign.operands) {
            throw Exception("Invalid types: ${operand1.returnType} ${sign.operands} ${operand2.returnType}")
        }
    }

    override fun plus(e: Expression): Expression {
        val other = e.eval()

        if (other is ConstantExpression && other.value == 0)
            return this.eval()

        return BinaryExpression(this, Sign.PLUS, other)
    }

    override fun minus(e: Expression): Expression {

        val other = e.eval()

        if (other is ConstantExpression && other.value == 0)
            return this.eval()

        return BinaryExpression(this.eval(), Sign.MINUS, other)
    }

    override fun times(e: Expression): Expression {

        val other = e.eval()

        if (other is ConstantExpression) {
            when (other.value) {
                0 -> return other
                1 -> return this
            }
        }

        return BinaryExpression(this.eval(), Sign.MULTIPLY, e)
    }

    override fun eval(): Expression {
        if (sign !in listOf(Sign.PLUS, Sign.MINUS, Sign.MULTIPLY)) {
            return this
        }

        println("Eval: " + operand1.javaClass.toString() + sign.text + operand2.javaClass.toString())


        val e1 = if (operand1 is BinaryExpression) operand1.eval() else operand1
        val e2 = if (operand2 is BinaryExpression) operand2.eval() else operand2

        println("ops evaled")

//        println(operand1.toString() + sign.text + operand2.toString())
//        println(e1.toString() + sign.text + e2.toString())


        val res = when (sign) {
            Sign.MULTIPLY -> e1 * e2
            Sign.PLUS -> e1 + e2
            Sign.MINUS -> e1 - e2
            else -> throw Exception("Never reached")
        }

        println(res.toString())

        return res

//        if (sign == Sign.MULTIPLY) {
////            when {
////                e1 is ElementExpression && e2 is ElementExpression -> {
////
////                    println("1:"+e1.toString())
////                    println(e2.toString())
////
////                    return ElementExpression(
////                        e1.value,
////                        e1.count * e2.count,
////                        e1.power + e2.power
////                    )
////                }
////                e1 is ElementExpression && e2 is ConstantExpression -> return ElementExpression(
////                    e1.value,
////                    e1.count * e2.value,
////                    e1.power
////                )
////                e2 is ElementExpression && e1 is ConstantExpression -> return ElementExpression(
////                    e2.value,
////                    e2.count * e1.value,
////                    e2.power
////                )
////            }
//
//            return e1 * e2
//
//
//        }

//        if (sign == Sign.PLUS || sign == Sign.MINUS) {



//            when {
//                e1 is ElementExpression && e2 is ElementExpression && e1.power == e2.power -> return ElementExpression(
//                    e1.value,
//                    sign.useSign(e1.count, e2.count),
//                    e1.power
//                ).eval()
//            }
//        }

//        if (sign == Sign.PLUS) {
//            return e1 + e2
//        }
//
//        if (sign == Sign.MINUS) {
//            return e1 - e2
//        }

//        if (e2 is ConstantExpression && e2.value == 1 && sign == Sign.MULTIPLY)
//            return e1
//
//        if (e1 is ConstantExpression && e1.value == 1 && sign == Sign.MULTIPLY)
//            return e2
//
//        if (e1 is ConstantExpression && e2 is ConstantExpression) {
//            return ConstantExpression(sign.useSign(e1.value, e2.value))
//        }

        println("rrrrrrr")

//        if (e1 is BinaryExpression) {
//            if (sign == Sign.MULTIPLY) {
//                println("qqq")
//                return BinaryExpression(
//                    BinaryExpression(e1.operand1.eval(), sign, e2),
//                    e1.sign,
//                    BinaryExpression(e1.operand2.eval(), sign, e2)
//                ).eval()
//            } else if (e1.sign != Sign.MULTIPLY) {
//                val test1 = BinaryExpression(e1.operand1.eval(), sign, e2).eval()
//
//                if (test1 is ConstantExpression) {
//                    return BinaryExpression(test1, e1.sign, e1.operand2).eval()
//                }
//
//                val test2 = BinaryExpression(e2, e1.sign, e1.operand2).eval()
//
//                if (test2 is ConstantExpression) {
//                    return BinaryExpression(e1.operand1, sign, test2).eval()
//                }
//            }
//        } else if (e2 is BinaryExpression) {
//            if (sign == Sign.MULTIPLY) {
//                return BinaryExpression(
//                    BinaryExpression(e1, sign, e2.operand1.eval()),
//                    e2.sign,
//                    BinaryExpression(e1, sign, e2.operand2.eval())
//                ).eval()
//            }
//
//            if (e2.sign != Sign.MULTIPLY) {
//                val test1 = BinaryExpression(e1, sign, e2.operand1.eval()).eval()
//
//                if (test1 is ConstantExpression) {
//                    return BinaryExpression(test1, e2.sign, e2.operand2).eval()
//                }
//
//                val test2 = BinaryExpression(e1, e2.sign, e2.operand2.eval()).eval()
//
//                if (test2 is ConstantExpression) {
//                    return BinaryExpression(test2, sign, e2.operand1).eval()
//                }
//            }
//        }
//
//        println("thus")

        return this
    }

    override fun toString(): String {
        val e1 = operand1.eval()

        if (e1 is ConstantExpression && e1.value == 0) {
            return if (sign == Sign.MULTIPLY) {
                "0"
            } else {
                operand2.eval().toString()
            }
        }

        val e2 = operand2.eval()

        if (e2 is ConstantExpression && e2.value == 0) {
            return if (sign == Sign.MULTIPLY) {
                "0"
            } else {
                operand1.eval().toString()
            }
        }

        return "(" + operand1.eval().toString() + sign.text + operand2.eval().toString() + ")"
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
        AND("&", Value.Type.BOOL, Value.Type.BOOL),
        OR("|", Value.Type.BOOL, Value.Type.BOOL), ;

        companion object {
            val order = listOf(
                listOf(MULTIPLY),
                listOf(PLUS, MINUS)
            )
        }

        fun useSign(i: Int, i2: Int): Int {
            return when (text) {
                "+" -> i + i2
                "-" -> i - i2
                "*" -> i * i2
                else -> throw Exception("Only for int")
            }
        }

        override fun toString(): String {
            return text
        }
    }
}