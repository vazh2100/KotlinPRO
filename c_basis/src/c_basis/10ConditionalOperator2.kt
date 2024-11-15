package c_basis

fun main() {
//    val temp = readln().toInt()
//    val hot = temp > 25
//    val cold = !hot && temp < 20
//    if (hot) {
//        println("The AC is on")
//    } else if (cold) {
//        println("The AC is off")
//    } else {
//        println("The AC is idle")
//    }


//    val time = readln().toInt() //0-24
//    val weather = readln().toBoolean()
//    val day = time in 7..19
//    val night = time < 7 || time > 19
//    if (weather && day ) {
//        println("Walk")
//    } else if (day) {
//        println("Read Book")
//    } else {
//        println("Sleep")
//    }


    val money = readln().toInt() //0-24
    val isHungry = readln().toBoolean()
    if (money >= 500 && isHungry) {
        println("Пицца")
    } else if (isHungry) {
        println("Доширак")
    } else if (money >= 500) {
        println("Кино")
    } else {
        println("Прогулка")
    }

}