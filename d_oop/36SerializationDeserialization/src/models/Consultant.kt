package models

import kotlin.random.Random

class Consultant(name: String, age: Byte? = null) : Worker(name, age) {

    override fun work() {
        super.work()
        print("I am serving clients...\n")
    }

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