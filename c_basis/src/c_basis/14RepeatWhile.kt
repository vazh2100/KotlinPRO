package c_basis

fun main() {
//    var acON = false
//    var temp = readln().toInt()
//    repeat(100) {
//        if (temp > 25) {
//            if (!acON) {
//                println("The AC is on")
//            }
//            acON = true
//        } else if (temp < 20) {
//            if (acON) {
//                println("The AC is off")
//            }
//            acON = false
//        }
//        if (acON) {
//            temp--
//        } else {
//            temp++
//        }
//        println("The temp is $temp")
//    }


//   val age = readln().toInt()
//    repeat(age){
//        println("C Днём Рождения")
//    }

//    val guessedNumber = Random.nextInt(1, 101)
//    println("Угадай число от 1 до 100: ")
//    var guessed = false
//    while (!guessed) {
//        val num = readln().toInt()
//        if (num == guessedNumber) {
//            guessed = true
//            println("Вы угадали число! $guessedNumber == $num")
//        } else if (num > guessedNumber) {
//            println("Число меньше")
//        } else {
//            println("Число больше")
//        }
//    }

//    println("Сколько лет?")
//    val age = readln().toInt()
//
//        repeat(18 - age){
//            println("Прошёл год")
//        }
//        println("Проходи")


    println("Сколько лет?")
    var age = readln().toInt()
    while (age < 18) {
        println("Прошёл год")
        age++
    }
    println("Проходи")


}