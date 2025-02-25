package g_fractional_num

fun main() {
//    val apples = 10f
//    val peoples = 4f
//    val result = apples / peoples
//    println(result)

//    val first = readln().toDouble()
//    val second = readln().toDouble()
//    val third = readln().toDouble()
//    val average = (first + second + third) / 3
//    println(average)

    val first = readln().toDouble()
    val second = readln().toDouble()
    val sign = readln()
    val result: Double = when (sign) {
        "+" -> first + second
        "-" -> first - second
        "*" -> first * second
        "/" -> first / second
        else -> 0.0

    }
    println(result)

}