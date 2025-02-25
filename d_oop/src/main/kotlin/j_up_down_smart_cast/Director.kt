package j_up_down_smart_cast

import i_inheritance.entities.Assistant
import i_inheritance.entities.Worker

class Director(name: String, age: Byte) : Worker(name, age) {

    fun getCoffee(assistant: Assistant, requestedCoffeeType: String) {
        val coffeeType = assistant.bringCoffee(coffeeType = requestedCoffeeType)
        println("Thank you, ${assistant.name}. $coffeeType is good.")
    }

    fun forceConsultantToWork(consultant: Consultant) {
        val count = consultant.serveClients()
        println("Отчёт директору: консультант ${consultant.name} обслужил $count клиентов.")

    }
}