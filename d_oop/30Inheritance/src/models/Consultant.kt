package models

import kotlin.random.Random

class Consultant(val name: String, val age: Int? = null) {

    fun sayHello() {
        val ageMessage = age?.let { " I'am $it years old." } ?: ""
        println("Hi, my name is $name.$ageMessage")
    }

    fun serveClients(): Int {
        val count = Random.nextInt(100)
        repeat(count) {
            println("Serve client")
        }
        println("I have served $count clients.")
        return count
    }
}