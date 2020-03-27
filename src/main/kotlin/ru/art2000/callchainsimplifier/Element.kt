package ru.art2000.callchainsimplifier

import ru.art2000.callchainsimplifier.expression.*

object Element {

    val TRUE_FILTER = BinaryExpression(
        constantOne,
        BinarySign.GREATER,
        constantZero
    )
    val FALSE_FILTER = BinaryExpression(
        constantOne,
        BinarySign.LESS,
        constantZero
    )

    val MAP_DEFAULT = ElementExpression(1, 1, 0)
//        object : ElementExpression() {
//        override fun toString(): String {
//            return "element"
//        }
//    }

    var filter: Expression = TRUE_FILTER
        private set

    fun applyFilter(f: Expression) {
        require(f.returnType == Value.Type.BOOL)

        filter = when (filter) {
            TRUE_FILTER -> f
            FALSE_FILTER -> FALSE_FILTER
            else -> BinaryExpression(
                filter,
                BinarySign.AND,
                f
            ).logicEval()
        }
    }

    var map: Expression = MAP_DEFAULT
        private set

    fun applyTransformation(t: Expression) {
        require(t.returnType == Value.Type.INT)

        map = if (filter == FALSE_FILTER) MAP_DEFAULT else t
    }

    override fun toString(): String {
        return "filter{$filter}%>%map{${map}}"
    }
}

