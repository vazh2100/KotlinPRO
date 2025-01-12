package entities.calculator

class LoggingCalculator : Calculator {

    override fun sum(a: Int, b: Int): Int {
        val result = a + b
        println("Operation: $a + $b = $result")
        return result
    }

    override fun subtract(a: Int, b: Int): Int {
        val result = a - b
        println("Operation: $a - $b = $result")
        return result
    }

    override fun multiple(a: Int, b: Int): Int {
        val result = a * b
        println("Operation: $a * $b = $result")
        return result
    }

    override fun divide(a: Int, b: Int): Double {
        val result = a / b.toDouble()
        println("Operation: $a / $b = $result")
        return result
    }
}