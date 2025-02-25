package k_ranges

fun main() {
//    val numbers = 0..1000
//    for (number in numbers) {
//        println(number)
//    }

//    val numbers2 = 0..<1000 // 0 until 1000
//    for (number in numbers) {
//        println(number)
//    }

//    val numbers = 0..<1000  step 10// 0 until 1000
//    for (number in numbers) {
//        println(number)
//    }

    val numbers = 0..1000 step 2
    println("Число: ")
    val num = readln().toInt()
    if (num in numbers) {
        println("Входит в 0..1000 step 2")
    } else {
        println("Не входит в 0..1000 step 2")
    }
}