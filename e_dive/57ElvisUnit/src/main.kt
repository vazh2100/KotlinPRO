import repository.WorkersDataManager

fun main() {
    val assistant = WorkersDataManager.findAssistant()
    val director = WorkersDataManager.findDirector()
    if (assistant != null) {
        director?.getCoffee(assistant, "Cappuchino")
    }
    val directorSalary = assistant?.salary ?: 0
    val assistantSalary = director?.salary ?: 0
    val sum = directorSalary + assistantSalary
    println(sum)
    val f: Unit = func()
    println(f)
}

//Unit - это singleton object, класс пустышка. Все функции без возвращаемого типа, возвращает Unit. Аналог void
// в других языках.
fun func() {
    return
}