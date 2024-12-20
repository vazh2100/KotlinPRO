package entities

import kotlin.test.Test
import kotlin.test.assertEquals

class CalculatorTest {
    private val calculator = Calculator()

    @Test
    fun `sum 10 and 5 is 15`() {
        val actual = calculator.sum(10, 5)
        val expected = 15
        assertEquals(expected, actual)
    }

    @Test
    fun `sum 100 and 50 is 150`() {
        val actual = calculator.sum(100, 50)
        val expected = 150
        assertEquals(expected, actual)
    }

    @Test
    fun `subtract 100 and 50 is 50`() {
        val actual = calculator.subtract(100, 50)
        val expected = 50
        assertEquals(expected, actual)
    }


    @Test
    fun `subtract 10 and 5 is 5`() {
        val actual = calculator.subtract(10, 5)
        val expected = 5
        assertEquals(expected, actual)
    }


    @Test
    fun `multiple 100 and 50 is 5000`() {
        val actual = calculator.multiple(100, 50)
        val expected = 5000
        assertEquals(expected, actual)
    }

    @Test
    fun `multiple 0 and 5 is 0`() {
        val actual = calculator.multiple(0, 5)
        val expected = 0
        assertEquals(expected, actual)
    }

    @Test
    fun `divide 10 and 4 is 2,5`() {
        val actual = calculator.divide(10, 4)
        val expected = 2.5
        assertEquals(expected, actual)
    }

    @Test
    fun `divide 0 and 50 is 0`() {
        val actual = calculator.divide(0, 50)
        val expected = 0.0
        assertEquals(expected, actual, 0.0)
    }

    @Test
    fun `divide 101 and 0 is Infinity`() {
        val actual = calculator.divide(101, 0)
        val expected = Double.POSITIVE_INFINITY
        assertEquals(expected, actual)
    }


    @Test
    fun test() {
        var a = 0.0
        repeat(1000000000) {
            a += 0.01
        }
        val expected = 10000000.0
        assertEquals(expected, a, 0.18)
    }
}