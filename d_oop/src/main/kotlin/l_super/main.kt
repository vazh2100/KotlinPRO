package l_super

import k_polymorphism.Assistant
import k_polymorphism.Consultant
import k_polymorphism.Director

fun main() {
    val director = Director("Andrey", 30)
    val assistant = Assistant("Elena")
    val consultant = Consultant("Artem")
    val accountant = Accountant("Petr", 30)
    val list = listOf(director, assistant, consultant, accountant)
    for (worker in list) {
        worker.work()
    }

}