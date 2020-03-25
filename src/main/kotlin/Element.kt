object Element {

    public const val TRUE_FILTER = "(1>0)"
    public const val FALSE_FILTER = "(1<0)"

    public const val MAP_DEFAULT = "element"

    var filter: String = TRUE_FILTER
        private set

    val filters = mutableListOf<BinaryExpression>()

    fun applyFilter(f: BinaryExpression) {
        require(f.returnType == Value.Type.BOOL)

        filters.add(f)

        filter = when (filter) {
            TRUE_FILTER -> "$f"
            FALSE_FILTER -> FALSE_FILTER
            else -> "($filter&$f)"
        }
    }

    var map = MAP_DEFAULT
        private set

    val transformations = mutableListOf<Expression>()

    val defaultMapExpression = ElementExpression("element")

    init {
        transformations.add(defaultMapExpression)
    }

    fun applyTransformation(t: Expression) {
        require(t.returnType == Value.Type.INT)

        if (filter == FALSE_FILTER) {
            map = MAP_DEFAULT
            return
        }

        transformations.add(t.eval())

        val previous = map

//        println("t:"+t.toString())
//        println("p:"+map)

        map = when {

            else -> t.eval().toString()
        }

//        println("m:"+map)
    }

    var expr: ElementExpression = ElementExpression("element")

    override fun toString(): String {
        return "filter{$filter}%>%map{${transformations.last()}}"
    }
}

