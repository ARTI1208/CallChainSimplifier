package ru.art2000.callchainsimplifier

import ru.art2000.callchainsimplifier.expression.*

object Element {

    val transformations = mutableListOf<Expression>()

    val TRUE_FILTER = BinaryExpression(
        constantOne,
        BinaryExpression.Sign.GREATER,
        constantZero
    )
    val FALSE_FILTER = BinaryExpression(
        constantOne,
        BinaryExpression.Sign.LESS,
        constantZero
    )

    const val MAP_DEFAULT = "element"

    var filter: Expression = TRUE_FILTER
        private set

    val filters = mutableListOf<Expression>()

    fun applyFilter(f: Expression) {
        require(f.returnType == Value.Type.BOOL)

        filter = when (filter) {
            TRUE_FILTER -> f
            FALSE_FILTER -> FALSE_FILTER
//            else -> "($filter&$f)"
            else -> BinaryExpression(
                filter,
                BinaryExpression.Sign.AND,
                f
            )
        }

        filters.add(f)
    }

    var map = MAP_DEFAULT
        private set

    val defaultMapExpression = object : ElementExpression() {
        override fun toString(): String {
            return "element"
        }
    }

    init {
        transformations.add(defaultMapExpression)
    }

    fun applyTransformation(t: Expression) {
        require(t.returnType == Value.Type.INT)

        if (filter == FALSE_FILTER) {
            transformations.clear()
            transformations.add(defaultMapExpression)
            return
        }

        transformations.add(t.eval())
    }

    override fun toString(): String {
        return "filter{$filter}%>%map{${transformations.last()}}"
    }
}

