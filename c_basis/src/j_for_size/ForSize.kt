package j_for_size

fun main() {
//    val names = listOf("Andrey", "Vasya", "Petya", "Lost", "Emma")
//    val searchName = readln()
//    var index = 0
//    while (index < names.size) {
//        if (searchName == names[index]) {
//            println("Такое имя есть")
//            return
//        }
//        index++
//    }
//    println("Такого имени нет")

//    val names = listOf("Andrey", "Vasya", "Petya", "Lost", "Emma")
//    val searchName = readln()
//    for (name: String in names) {
//        if (searchName == name) {
//            println("Такое имя есть")
//            return
//        }
//    }

//    for (i in 0 until names.size) {
//        if (searchName == names[i]) {
//            println("Такое имя есть")
//            return
//        }
//    }
//    println("Такого имени нет")


    val nums = listOf(1, 2, 5, 8, 90, 34, 21, 65, 87, 35, 12, 57)
    val nums2 = 0..100 step 3

    for (num in nums) {
        if (num % 2 == 0) println("$num is even") else println("$num is odd")
    }

}