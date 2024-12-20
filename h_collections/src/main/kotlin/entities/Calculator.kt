package entities

class Calculator {

    fun sum(
        a: Int,
        b: Int
    ): Int = a + b

    fun subtract(
        a: Int,
        b: Int
    ): Int = a - b

    fun multiple(
        a: Int,
        b: Int
    ): Int = a * b

    fun divide(
        a: Int,
        b: Int
    ): Double = a.toDouble() / b
}