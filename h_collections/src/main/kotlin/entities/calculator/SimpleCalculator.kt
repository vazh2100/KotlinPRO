package entities.calculator

class SimpleCalculator : Calculator {

    override fun sum(a: Int, b: Int): Int = a + b

    override fun subtract(a: Int, b: Int): Int = a - b

    override fun multiple(a: Int, b: Int): Int = a * b

    override fun divide(a: Int, b: Int): Double = a.toDouble() / b
}