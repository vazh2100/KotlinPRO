package models


class Director : Worker {

    constructor(name: String, age: Byte) : super(name, age, WorkerType.DIRECTOR)
    constructor(map: Map<String, String>) : super(map)


    override fun work() {
        super.work()
        print("I am drinking coffee...\n")
    }

    fun getCoffee(assistant: Assistant, requestedCoffeeType: String) {
        val coffeeType = assistant.bringCoffee(coffeeType = requestedCoffeeType)
        println("Thank you, ${assistant.name}. $coffeeType is good.")
    }

    fun forceConsultantToWork(consultant: Consultant) {
        val count = consultant.serveClients()
        println("Отчёт директору: консультант ${consultant.name} обслужил $count клиентов.")

    }
}