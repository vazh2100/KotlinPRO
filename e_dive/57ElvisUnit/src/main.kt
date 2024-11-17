import repository.WorkersDataManager

fun main() {
    val assistant = WorkersDataManager.findAssistant()
    val director = WorkersDataManager.findDirector()
    if (assistant != null) {
        director?.getCoffee(assistant, "Cappuchino")
    }

}