package entities

import kotlin.random.Random

class Consultant : Worker {

    constructor(name: String, age: Byte) : super(name, age, WorkerType.CONSULTANT)
    constructor(map: Map<String, String>) : super(map)

    override fun work() {
        print("I am serving clients...\n")
    }

    fun sayHello() {
        val ageMessage = age.let { " I am $it years old." }
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