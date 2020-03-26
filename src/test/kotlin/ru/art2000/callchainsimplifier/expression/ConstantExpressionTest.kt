package ru.art2000.callchainsimplifier.expression

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ConstantExpressionTest {

    @Test
    fun compatibility() {
        assertEquals(
            constantZero,
            ConstantExpression(0)
        )
        assertEquals(
            constantZero,
            ElementExpression(0, 0, 0)
        )
        assertEquals(
            constantZero, constantZero * ElementExpression(
                1,
                1,
                1
            )
        )

        val el = ElementExpression(3, 4, 5)

        assertEquals(el, constantOne * el)
        assertEquals(el, constantZero + el)
        assertEquals(-el, constantZero - el)
    }

    @Test
    fun plus() {
        assertEquals(constantZero, constantZero + constantZero)
        assertEquals(constantOne, constantZero + constantOne)
        assertEquals(constantOne, constantOne + constantZero)
        assertEquals(ConstantExpression(2), constantOne + constantOne)
    }

    @Test
    fun minus() {
        assertEquals(constantZero, constantZero - constantZero)
        assertEquals(ConstantExpression(-1), constantZero - constantOne)
        assertEquals(constantOne, constantOne - constantZero)
        assertEquals(constantZero, constantOne - constantOne)
    }

    @Test
    fun times() {
        assertEquals(constantZero, constantZero * constantZero)
        assertEquals(constantZero, constantZero * constantOne)
        assertEquals(constantZero, constantOne * constantZero)
        assertEquals(constantOne, constantOne * constantOne)


        val constantTwo = ConstantExpression(2)

        assertEquals(constantTwo, constantTwo * constantOne)
        assertEquals(constantTwo, constantOne * constantTwo)

        assertEquals(ConstantExpression(4), constantTwo * constantTwo)
        assertEquals(ConstantExpression(-4), -constantTwo * constantTwo)
    }

    @Test
    fun compareTo() {
        assertTrue(constantZero < constantOne)
        assertTrue(constantZero <= constantOne)
        assertTrue(constantOne > constantZero)
        assertTrue(constantOne >= constantZero)

        assertFalse(constantOne > constantOne)
        assertTrue(constantOne >= constantOne)
        assertTrue(constantOne <= constantOne)

        assertTrue(
            ConstantExpression(-5) < ConstantExpression(
                -3
            )
        )
        assertTrue(
            ConstantExpression(-5) < ConstantExpression(
                16
            )
        )
        assertTrue(
            ConstantExpression(-5) > ConstantExpression(
                -44
            )
        )
    }

    @Test
    fun unaryMinus() {
        assertEquals(constantZero, -constantZero)
        assertEquals(ConstantExpression(-1), -constantOne)
        assertEquals(constantOne, -(-constantOne))
    }
}