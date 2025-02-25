package m_if_when

fun main() {
//    print("Введи месяц: ")
//    val num = readln().toInt()
//    val num2 = 2
//    val month: String = when (num) {
//        1 -> "January"
//        2 -> "February"
//        3 -> "March"
//        4 -> "April"
//        5 -> "May"
//        6 -> "June"
//        7 -> "July"
//        8 -> "August"
//        9 -> "September"
//        10 -> "October"
//        11 -> "November"
//        12 -> "December"
//        else -> "Нет такого месяца"
//    }
//
//    val jam = when {
//        num == 1 -> "S"
//        num == 2-> "D"
//
//        else -> "L"
//    }
//    println("$num: $month")
//    println(jam)

//    print("Введи месяц словом: ")
//    val month = readln()
//    val season = when (month) {
//        "January", "February", "December" -> "Winter"
//        "March", "April", "May" -> "Spring"
//        "June", "July", "August" -> "Summer"
//        "September", "October", "November" -> "Autumn"
//        else -> "Нет такого месяца"
//    }
//    println("$month is $season")

    print("Введи месяц числом: ")
    val month = readln().toInt()
    val season = when (month) {
        12, 1, 2 -> "Winter"
        in 3..5 -> "Spring"
        in 6..8 -> "Summer"
        in 9..11 -> "Autumn"
        else -> "Нет такого месяца"
    }
    println("$month is $season")
}