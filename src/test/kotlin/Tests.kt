import org.junit.Test
import kotlin.test.assertTrue


class Tests {
    val tests = arrayOf(
        "map{(element+10)}%>%map{(element-10)}%>%map{(element*element)}",
        "filter{(element>10)}%>%filter{(element<20)}",
        "map{(element+10)}%>%filter{(element>10)}%>%map{(element*element)}",
        "filter{(element>0)}%>%filter{(element<0)}%>%map{(element*element)"
    )

    @Test
    fun test1() {
        tests.forEach {
            println(simplify(it))
        }

        assertTrue(true)
    }
}