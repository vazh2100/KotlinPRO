import models.Assistant
import models.Consultant
import models.Director


fun main() {
    val director = Director("Andrey", 30)
    val assistant = Assistant("Elena")
    val consultant = Consultant("Artem")
    director.getCoffee(assistant, "Americano")
    director.forceConsultantToWork(consultant)
}