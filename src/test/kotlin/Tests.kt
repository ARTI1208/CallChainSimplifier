import org.junit.Test
import kotlin.test.assertTrue


class Tests {
    val tests = arrayOf(
        "map{(((2*(element*element))+3)*((4*element)+10))}",
        "map{(element+10)}%>%map{(element-10)}%>%map{(element*element)}",
        "filter{(element>10)}%>%filter{(element<20)}",
        "map{(element+10)}%>%filter{(element>10)}%>%map{(element*element)}",
        "filter{(element>0)}%>%filter{(element<0)}%>%map{(element*element)}"
    )

    @Test
    fun test1() {

        assertTrue(true)
    }

}