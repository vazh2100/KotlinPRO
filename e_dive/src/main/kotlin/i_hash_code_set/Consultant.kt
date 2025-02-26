package i_hash_code_set

import a_get_set_1.WorkerType

class Consultant : Worker {

    constructor(name: String, age: Byte, salary: Int) : super(
        name = name, age = age, workerType = WorkerType.CONSULTANT, salary = salary
    )

    constructor(map: Map<String, String>) : super(map = map)

    override fun copy(name: String, age: Byte, salary: Int): Consultant {
        return Consultant(name, age, salary)
    }

    override fun work() {
        print("I am serving clients...\n")
    }

    fun sayHello() {
        val ageMessage = age.let { " I am $it years old." }
        println("Hi, my name is $name.$ageMessage")
    }

}