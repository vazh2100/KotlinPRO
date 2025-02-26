package k_nullable

fun main() {
    val assistant = WorkersDataManager.findAssistant()
    val director = WorkersDataManager.findDirector()
    if (assistant != null) {
        director?.getCoffee(assistant, "Cappuccino")
    }

}