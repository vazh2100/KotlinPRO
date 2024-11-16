package entities


data class Director(
    override val id: Int,
    override val name: String,
    override val age: Byte,
    override val salary: Int,
) : Worker(id, name, age, salary, WorkerType.DIRECTOR) {

    override fun work() {
        print("I am drinking coffee...\n")
    }

    override fun copy(
        age: Byte,
        salary: Int
    ): Worker {
        return copy(id = id, name = name, age = age, salary = salary)
    }


    fun getCoffee(
        assistant: Assistant,
        requestedCoffeeType: String
    ) {
        val coffeeType = assistant.bringCoffee(coffeeType = requestedCoffeeType)
        println("Thank you, ${assistant.name}. $coffeeType is good.")
    }

    fun forceConsultantToWork(consultant: Consultant) {
        val count = consultant.serveClients()
        println("Отчёт директору: консультант ${consultant.name} обслужил $count клиентов.")

    }

    companion object {
        fun fromJson(map: Map<String, String>): Director {
            val name = map["name"]!!
            val age = map["age"]!!.toByte()
            val salary = map["salary"]!!.toInt()
            val id = map["id"]!!.toInt()
            return Director(id, name, age, salary)
        }
    }
}