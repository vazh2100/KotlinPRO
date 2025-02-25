package k_polymorphism

fun main() {
    val director = Director("Andrey", 30)
    val assistant = Assistant("Elena")
    val consultant = Consultant("Artem")
    val list = listOf(director, assistant, consultant)
    for (worker in list) {
        worker.work()
    }

}