package ru.art2000.callchainsimplifier.expression

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ElementExpressionTest {

    @Test
    fun unaryMinus() {
        assertEquals(ElementExpression(-1, 1, 0), -ElementExpression())
        assertEquals(ElementExpression(-3, 4, -5), -ElementExpression(3, 4, 5))

        val el = ElementExpression(7, 8, -9)

        assertEquals(el, -(-el))
    }

    @Test
    fun plus() {
        assertEquals(ElementExpression(5, 4, 0), ElementExpression(3, 4, 5) + ElementExpression(2, 4, -5))
        assertEquals(ElementExpression(2, 4, -10), ElementExpression(0, 2, -5) + ElementExpression(2, 4, -5))
        assertEquals(ElementExpression(0, 0, 2), ElementExpression(0, 2, 7) + ElementExpression(0, 4, -5))
        assertEquals(ConstantExpression(2), ElementExpression(0, 2, 7) + ElementExpression(0, 4, -5))

        val nonEvalLeft = ElementExpression(3, 2, 7)
        val nonEvalRight = ElementExpression(2, 4, -5)

        assertEquals(
            BinaryExpression(nonEvalLeft, BinaryExpression.Sign.PLUS, nonEvalRight),
            nonEvalLeft + nonEvalRight
        )
    }

    @Test
    fun minus() {
        assertEquals(ElementExpression(1, 4, 10), ElementExpression(3, 4, 5) - ElementExpression(2, 4, -5))
        assertEquals(ElementExpression(-2, 4, 0), ElementExpression(0, 2, -5) - ElementExpression(2, 4, -5))
        assertEquals(ElementExpression(0, 0, 12), ElementExpression(0, 2, 7) - ElementExpression(0, 4, -5))

        val nonEvalLeft = ElementExpression(3, 2, 7) //3*e^2+7
        val nonEvalRight = ElementExpression(2, 4, -5) //2*e^4-5

        //3*e^2 - 2*e^4 + 12

        assertEquals(
            BinaryExpression(
                BinaryExpression(
                    ElementExpression(3, 2, 0),
                    BinaryExpression.Sign.MINUS,
                    ElementExpression(2, 4, 0)
                ), BinaryExpression.Sign.PLUS, ConstantExpression(12)
            ), nonEvalLeft - nonEvalRight
        )
    }

    @Test
    fun times() {

        assertEquals(
            BinaryExpression(
                ElementExpression(6, 8, -25),
                BinaryExpression.Sign.PLUS,
                ElementExpression(-5, 4, 0)
            ),

            ElementExpression(3, 4, 5) * ElementExpression(2, 4, -5)
        )

        assertEquals(ElementExpression(-10, 4, 25), ElementExpression(0, 2, -5) * ElementExpression(2, 4, -5))
        assertEquals(ConstantExpression(-35), ElementExpression(0, 2, 7) * ElementExpression(0, 4, -5))

        val left = ElementExpression(3, 2, 7)
        val right = ElementExpression(2, 4, -5)

        assertEquals(
            BinaryExpression(
                ElementExpression(6, 6, -35),
                BinaryExpression.Sign.PLUS,
                BinaryExpression(
                    ElementExpression(14, 4),
                    BinaryExpression.Sign.PLUS,
                    ElementExpression(-15, 2)
                )
            ),
            left * right
        )

    }

    @Test
    fun compareTo() {
        assertTrue(ElementExpression(1, 2, 3) < ElementExpression(1, 2, 4))
        assertTrue(ElementExpression(0, 3, 3) < ElementExpression(2, 2, 5))
        assertTrue(ElementExpression(0, 3, 7) < ElementExpression(2, 2, 3))
    }
}