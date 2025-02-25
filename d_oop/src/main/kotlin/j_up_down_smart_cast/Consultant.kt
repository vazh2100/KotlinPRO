package j_up_down_smart_cast

import i_inheritance.entities.Worker
import kotlin.random.Random

class Consultant(name: String, age: Byte? = null) : Worker(name, age) {

    fun sayHello() {
        val ageMessage = age?.let { " I'm $it years old." } ?: ""
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