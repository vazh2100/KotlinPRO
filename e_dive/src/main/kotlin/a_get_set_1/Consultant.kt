package a_get_set_1

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

}