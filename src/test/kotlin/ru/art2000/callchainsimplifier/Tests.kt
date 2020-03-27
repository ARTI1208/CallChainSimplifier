package ru.art2000.callchainsimplifier

import org.junit.Test
import kotlin.test.assertEquals


class Tests {

    private val simplifier = Simplifier()

    private val tests = arrayOf(

        "map{(((2*(element*element))+3)*((4*element)+10))}"
                to
                "filter{(1>0)}%>%map{(((8*((element*element)*element))+30)+((20*(element*element))+(12*element)))}",

        "map{(element+10)}%>%map{(element-10)}%>%map{(element*element)}"
                to
                "filter{(1>0)}%>%map{(element*element)}",

        "filter{(element>10)}%>%filter{(element<20)}"
                to
                "filter{((element>10)&(element<20))}%>%map{element}",

        "map{(element+10)}%>%filter{(element>10)}%>%map{(element*element)}"
                to
                "filter{(element>0)}%>%map{(((element*element)+(20*element))+100)}",

        "filter{(element>0)}%>%filter{(element<0)}%>%map{(element*element)}"
                to
                "filter{(1<0)}%>%map{element}"
    )

    @Test
    fun test() {
        tests.forEach {
            assertEquals(it.second, simplifier.simplify(it.first))
        }
    }

}