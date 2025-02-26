package j_function_let

import b_kotlin_serialization_2.PersonDataManager

var age: Int? = 20

fun main() {
//    if (age != null) {
//        if (age >= 18) { //из-за того что переменная мутабельная и не локальная проверка на null не спасает
//            println("Yes")
//        } else {
//            println("No")
//        }
//    }
    age?.let {
        if (it >= 18) {
            "Yes"
        } else {
            "No"
        }
    }?.let { println(it) }
    showEmail()

}

fun showEmail() {
    val id = readln().toInt()
    PersonDataManager.profiles
        .find { it.id == id }
        .let { it?.email ?: "Пользователь не найден" }
        .let { println(it) }
}






