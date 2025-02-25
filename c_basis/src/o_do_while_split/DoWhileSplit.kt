package o_do_while_split

fun main() {
//    var count = readln().toInt()
//    do {
//        println("Hello")
//        count--
//    } while (count > 0)
//
//    val daysString = "Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday"
//    val days = daysString.split(", ")
//    println(days)

    println("Введи выражение типа x + y")
    val string = readln()
    val chars = string.split(" ")
    val first = chars[0].toDouble()
    val sign = chars[1]
    val second = chars[2].toDouble()
    val result: Double = when (sign) {
        "+" -> first - second
        "-" -> first - second
        "*" -> first * second
        "/" -> first / second
        else -> 0.0

    }
    println(result)

}