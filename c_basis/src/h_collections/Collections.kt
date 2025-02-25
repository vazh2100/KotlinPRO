package h_collections

fun main() {
//    val names = listOf<String>("Vasya", "Petya", "Mark", "Lost")
//    val john = names[0]
//    val lost = names[3]
//    println(john)
//    println(lost)

    val daysInMonth = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    println("Введите порядковый номер месяца(1-12):")
    val number = readln().toInt()
    if (number < 1 || number > 12) {
        println("Некорректный номер месяца(1-12): $number")
    } else {
        val days = daysInMonth[number - 1]
        println("В $number месяце $days дней")
    }

}