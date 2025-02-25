package j_up_down_smart_cast

import i_inheritance.entities.Assistant
import i_inheritance.entities.Worker

fun main() {
    val director: Worker = Director("Andrey", 30)
    val assistant: Worker = Assistant("Elena")
    val consultant: Worker = Consultant("Artem")
    (director as Director).getCoffee(assistant as Assistant, "Americano")
    director.forceConsultantToWork(consultant as Consultant)
}