package e_conditions

fun main() {
//    val temp = readln().toInt()
//    if (temp > 25) {
//        println("The AC is on")
//    } else if (temp < 20) {
//        println("The AC is off")
//    } else {
//        println("The AC is idle")
//    }

    val firstAge = readln().toInt()
    val secondAge = readln().toInt()
    if (firstAge > secondAge) {
        println("first > second")
    } else if (firstAge == secondAge) {
        println("first = second")
    } else {
        println("first < second")
    }
}