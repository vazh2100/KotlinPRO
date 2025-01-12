package entities

import entities.calculator.Calculator
import entities.calculator.LoggingCalculator
import entities.calculator.SimpleCalculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource


internal class CalculatorTest {

    companion object {
        @JvmStatic
        private fun calculators() = listOf(SimpleCalculator(), LoggingCalculator())
    }

    @ParameterizedTest
    @MethodSource("calculators")
    fun `sum 10 and 5 is 15`(calculator: Calculator) {
        val actual = calculator.sum(10, 5)
        val expected = 15
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("calculators")
    fun `sum 100 and 50 is 150`(calculator: Calculator) {
        val actual = calculator.sum(100, 50)
        val expected = 150
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("calculators")
    fun `subtract 100 and 50 is 50`(calculator: Calculator) {
        val actual = calculator.subtract(100, 50)
        val expected = 50
        assertEquals(expected, actual)
    }


    @ParameterizedTest
    @MethodSource("calculators")
    fun `subtract 10 and 5 is 5`(calculator: Calculator) {
        val actual = calculator.subtract(10, 5)
        val expected = 5
        assertEquals(expected, actual)
    }


    @ParameterizedTest
    @MethodSource("calculators")
    fun `multiple 100 and 50 is 5000`(calculator: Calculator) {
        val actual = calculator.multiple(100, 50)
        val expected = 5000
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("calculators")
    fun `multiple 0 and 5 is 0`(calculator: Calculator) {
        val actual = calculator.multiple(0, 5)
        val expected = 0
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("calculators")
    fun `divide 10 and 4 is 2,5`(calculator: Calculator) {
        val actual = calculator.divide(10, 4)
        val expected = 2.5
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("calculators")
    fun `divide 0 and 50 is 0`(calculator: Calculator) {
        val actual = calculator.divide(0, 50)
        val expected = 0.0
        assertEquals(expected, actual, 0.0)
    }

    @ParameterizedTest
    @MethodSource("calculators")
    fun `divide 101 and 0 is Infinity`(calculator: Calculator) {
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