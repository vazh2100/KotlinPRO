package models

class Consultant(val name: String, val age: Int? = null) {

    fun sayHello() {
        val ageMessage = age?.let { " I'am $it years old." } ?: ""
        println("Hi, my name is $name.$ageMessage")
    }
}