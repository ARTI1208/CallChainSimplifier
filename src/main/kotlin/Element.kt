object Element {

    public const val TRUE_FILTER = "(1 > 0)"
    public const val FALSE_FILTER = "(1 < 0)"

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

    val transformations = mutableListOf<BinaryExpression>()

    fun applyTransformation(t: BinaryExpression) {
        require(t.returnType == Value.Type.INT)

        transformations.add(t)

        val previous = map

        when {
            t.operand1 is ElementExpression -> map = "($previous${t.sign}${t.operand2})"
            t.operand2 is ElementExpression -> map = "(${t.operand1}${t.sign}$previous)"
            else -> map = t.eval().toString()
        }
    }

    override fun toString(): String {
        return "filter{$filter}%>%map{$map}"
    }
}