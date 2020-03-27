package ru.art2000.callchainsimplifier.expression

import org.junit.Test
import ru.art2000.callchainsimplifier.Element
import kotlin.test.assertEquals

class BinaryExpressionTest {

    @Test
    fun eval() {
        // widely tested with logicEval and arithmetic operations
    }

    @Test
    fun logicEval() {
        assertEquals(
            Element.TRUE_FILTER,
            BinaryExpression(
                ConstantExpression(5),
                BinarySign.GREATER,
                ConstantExpression(3)
            ).logicEval()
        )

        assertEquals(
            Element.TRUE_FILTER,
            BinaryExpression(
                ConstantExpression(-5),
                BinarySign.LESS,
                ConstantExpression(3)
            ).logicEval()
        )

        assertEquals(
            Element.FALSE_FILTER,
            BinaryExpression(
                ConstantExpression(-5),
                BinarySign.EQUAL,
                ConstantExpression(-3)
            ).logicEval()
        )

        assertEquals(
            Element.FALSE_FILTER,
            BinaryExpression(
                ConstantExpression(3),
                BinarySign.GREATER,
                ConstantExpression(5)
            ).logicEval()
        )

        assertEquals(
            Element.TRUE_FILTER,
            BinaryExpression(
                ConstantExpression(3),
                BinarySign.EQUAL,
                ConstantExpression(3)
            ).logicEval()
        )

        assertEquals(
            Element.TRUE_FILTER,
            BinaryExpression(
                BinaryExpression(
                    ConstantExpression(3),
                    BinarySign.EQUAL,
                    ConstantExpression(3)
                ),
                BinarySign.OR,
                BinaryExpression(
                    ElementExpression(1, 1, 0),
                    BinarySign.GREATER,
                    constantZero
                )
            ).logicEval()
        )

        assertEquals(
            Element.FALSE_FILTER,
            BinaryExpression(
                BinaryExpression(
                    ConstantExpression(3),
                    BinarySign.EQUAL,
                    ConstantExpression(4)
                ),
                BinarySign.AND,
                BinaryExpression(
                    ElementExpression(1, 1, 0),
                    BinarySign.GREATER,
                    constantZero
                )
            ).logicEval()
        )


        val noEvalTest = BinaryExpression(
            BinaryExpression(
                ElementExpression(2, 1, 0),
                BinarySign.EQUAL,
                ConstantExpression(4)
            ),
            BinarySign.AND,
            BinaryExpression(
                ElementExpression(1, 1, 0),
                BinarySign.GREATER,
                constantZero
            )
        )

        assertEquals(noEvalTest, noEvalTest.logicEval())
    }

    @Test
    fun binaryPlus() {

        assertEquals(
            ElementExpression(2, 2, 5),
            BinaryExpression(
                ElementExpression(1, 2, 5),
                BinarySign.PLUS,
                ElementExpression(-4, 2, 11)
            ) + BinaryExpression(
                ElementExpression(7, 2, -5),
                BinarySign.PLUS,
                ElementExpression(-2, 2, -6)
            )
        )

        assertEquals(
            BinaryExpression(
                BinaryExpression(
                    ElementExpression(-3, 2),
                    BinarySign.PLUS,
                    ElementExpression(5, 3)
                ),
                BinarySign.PLUS,
                ConstantExpression(5)
            ),
            BinaryExpression(
                ElementExpression(1, 2, 5),
                BinarySign.PLUS,
                ElementExpression(-4, 2, 11)
            ) + BinaryExpression(
                ElementExpression(7, 3, -5),
                BinarySign.PLUS,
                ElementExpression(-2, 3, -6)
            )
        )

        assertEquals(
            BinaryExpression(
                BinaryExpression(
                    ElementExpression(5, 4),
                    BinarySign.PLUS,
                    ElementExpression(10, 5, -8)
                ),
                BinarySign.PLUS,
                BinaryExpression(
                    ElementExpression(2),
                    BinarySign.PLUS,
                    ElementExpression(1, 2, 3)
                )
            ),
            BinaryExpression(
                ElementExpression(5, 4),
                BinarySign.PLUS,
                BinaryExpression(
                    ElementExpression(2, 5, 8),
                    BinarySign.PLUS,
                    ElementExpression(8, 5, -16)
                )
            ) + BinaryExpression(
                ElementExpression(2),
                BinarySign.PLUS,
                ElementExpression(1, 2, 3)
            )
        )
    }

    @Test
    fun mixedPlus() {
        assertEquals(
            ElementExpression(1, 2),
            ElementExpression(-4, 2, 11) + BinaryExpression(
                ElementExpression(7, 2, -5),
                BinarySign.PLUS,
                ElementExpression(-2, 2, -6)
            )
        )

        assertEquals(
            ElementExpression(1, 2, 1),
            BinaryExpression(
                ElementExpression(7, 2, -5),
                BinarySign.PLUS,
                ElementExpression(-2, 2, -6)
            ) + ElementExpression(-4, 2, 12)
        )
    }

    @Test
    fun binaryMinus() {
        assertEquals(
            ElementExpression(-4, 2, -7),
            BinaryExpression(
                ElementExpression(1, 2, 5),
                BinarySign.MINUS,
                ElementExpression(-4, 2, 11)
            ) - BinaryExpression(
                ElementExpression(7, 2, -5),
                BinarySign.MINUS,
                ElementExpression(-2, 2, -6)
            )
        )

        assertEquals(
            BinaryExpression(
                BinaryExpression(
                    ElementExpression(5, 2),
                    BinarySign.MINUS,
                    ElementExpression(9, 3)
                ),
                BinarySign.PLUS,
                ConstantExpression(-7)
            ),
            BinaryExpression(
                ElementExpression(1, 2, 5),
                BinarySign.MINUS,
                ElementExpression(-4, 2, 11)
            ) - BinaryExpression(
                ElementExpression(7, 3, -5),
                BinarySign.MINUS,
                ElementExpression(-2, 3, -6)
            )
        )

        assertEquals(
            BinaryExpression(
                BinaryExpression(
                    ElementExpression(5, 4),
                    BinarySign.MINUS,
                    ElementExpression(-6, 5, 24)
                ),
                BinarySign.MINUS,
                BinaryExpression(
                    ElementExpression(2),
                    BinarySign.MINUS,
                    ElementExpression(1, 2, 3)
                )
            ),
            BinaryExpression(
                ElementExpression(5, 4),
                BinarySign.MINUS,
                BinaryExpression(
                    ElementExpression(2, 5, 8),
                    BinarySign.MINUS,
                    ElementExpression(8, 5, -16)
                )
            ) - BinaryExpression(
                ElementExpression(2),
                BinarySign.MINUS,
                ElementExpression(1, 2, 3)
            )
        )
    }

    @Test
    fun mixedMinus() {
        assertEquals(
            ElementExpression(-13, 2, 10),
            ElementExpression(-4, 2, 11) - BinaryExpression(
                ElementExpression(7, 2, -5),
                BinarySign.MINUS,
                ElementExpression(-2, 2, -6)
            )
        )

        assertEquals(
            ElementExpression(13, 2, -11),
            BinaryExpression(
                ElementExpression(7, 2, -5),
                BinarySign.MINUS,
                ElementExpression(-2, 2, -6)
            ) - ElementExpression(-4, 2, 12)
        )
    }

    @Test
    fun times() {

        assertEquals(
            BinaryExpression(
                BinaryExpression(
                    ElementExpression(-4, 4, 55),
                    BinarySign.PLUS,
                    ElementExpression(-9, 2)
                ),
                BinarySign.PLUS,
                BinaryExpression(
                    ElementExpression(-14, 4, 30),
                    BinarySign.PLUS,
                    ElementExpression(-32, 2)
                )
            ),
            BinaryExpression(
                ElementExpression(1, 2, 5),
                BinarySign.MULTIPLY,
                ElementExpression(-4, 2, 11)
            ) + BinaryExpression(
                ElementExpression(7, 2, -5),
                BinarySign.MULTIPLY,
                ElementExpression(-2, 2, -6)
            )
        )

        assertEquals(
            BinaryExpression(
                BinaryExpression(
                    ElementExpression(-4, 4, 55),
                    BinarySign.PLUS,
                    ElementExpression(-9, 2)
                ),
                BinarySign.MULTIPLY,
                BinaryExpression(
                    ElementExpression(-14, 4, 30),
                    BinarySign.PLUS,
                    ElementExpression(-32, 2)
                )
            ),
            BinaryExpression(
                ElementExpression(1, 2, 5),
                BinarySign.MULTIPLY,
                ElementExpression(-4, 2, 11)
            ) * BinaryExpression(
                ElementExpression(7, 2, -5),
                BinarySign.MULTIPLY,
                ElementExpression(-2, 2, -6)
            )
        )

    }

    @Test
    fun timesMixed() {
        val test = BinaryExpression(
            ElementExpression(1, 2, 5),
            BinarySign.MULTIPLY,
            ElementExpression(-4, 2, 11)
        ) * ElementExpression(0, 5, 0)

        assertEquals(
            constantZero,
            constantZero * test
        )

        assertEquals(
            constantZero,
            test * constantZero
        )


        assertEquals(
            test,
            constantOne * test
        )

        assertEquals(
            test,
            test * constantOne
        )

        assertEquals(
            test,
            -test * -constantOne
        )
    }

    @Test
    fun unaryMinus() {

        val test = BinaryExpression(
            BinaryExpression(
                ElementExpression(5, 2),
                BinarySign.MINUS,
                ElementExpression(9, 3)
            ),
            BinarySign.PLUS,
            ConstantExpression(-7)
        )

        assertEquals(
            BinaryExpression(
                BinaryExpression(
                    ElementExpression(-5, 2),
                    BinarySign.MINUS,
                    ElementExpression(-9, 3)
                ),
                BinarySign.PLUS,
                ConstantExpression(7)
            ),
            -test
        )


        assertEquals(test, -(-test))
    }
}