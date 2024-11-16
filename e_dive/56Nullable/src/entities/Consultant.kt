package entities

import kotlin.random.Random

data class Consultant(
    override val id: Int,
    override val name: String,
    override val age: Byte,
    override val salary: Int,
) : Worker(
    name = name, age = age, workerType = WorkerType.CONSULTANT, salary = salary, id = id
) {


    override fun work() {
        print("I am serving clients...\n")
    }

    override fun copy(
        age: Byte,
        salary: Int
    ): Worker {
        return Consultant(this.id, this.name, age, salary)
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

    companion object {
        fun fromJson(map: Map<String, String>): Consultant {
            val name = map["name"]!!
            val age = map["age"]!!.toByte()
            val salary = map["salary"]!!.toInt()
            val id = map["id"]!!.toInt()
            return Consultant(id, name, age, salary)
        }
    }


}