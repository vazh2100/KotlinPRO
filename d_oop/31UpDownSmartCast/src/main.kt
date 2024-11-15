import models.*


fun main() {
    val director: Worker = Director("Andrey", 30)
    val assistant: Worker = Assistant("Elena")
    val consultant: Worker = Consultant("Artem")
    (director as Director).getCoffee(assistant as Assistant, "Americano")
    director.forceConsultantToWork(consultant as Consultant)
}