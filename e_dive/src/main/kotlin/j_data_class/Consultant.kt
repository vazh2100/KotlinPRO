package j_data_class

import a_get_set_1.WorkerType

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