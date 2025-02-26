package j_data_class

import a_get_set_1.WorkerType

data class Assistant(
    override val id: Int,
    override val name: String,
    override val age: Byte,
    override val salary: Int,
) : Worker(id, name, age, salary, WorkerType.ASSISTANT) {


    override fun work() {
        print("I am bringing coffee...\n")
    }

    override fun copy(
        age: Byte,
        salary: Int
    ): Worker {
        return this.copy(id = id, name = name, age = age, salary = salary)
    }


    fun bringCoffee(coffeeType: String = "Cappuccino"): String {
        println("$name встал/а")
        println("Подошёл к кофе машине")
        println("Нажал кнопку $coffeeType")
        println("Взял кофе типа $coffeeType")
        println("Отнёс кофе")
        return coffeeType
    }

    companion object {
        fun fromJson(map: Map<String, String>): Assistant {
            val name = map["name"]!!
            val age = map["age"]!!.toByte()
            val salary = map["salary"]!!.toInt()
            val id = map["id"]!!.toInt()
            return Assistant(id, name, age, salary)
        }
    }
}