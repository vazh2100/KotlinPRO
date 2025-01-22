package entities.calculator

interface Calculator {
    fun sum(a: Int, b: Int): Int

    fun subtract(a: Int, b: Int): Int

    fun multiple(a: Int, b: Int): Int

    fun divide(a: Int, b: Int): Double
}
