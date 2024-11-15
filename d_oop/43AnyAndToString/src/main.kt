import entities.*



fun main() {
    val director = Director("Andrey", 30)
    val assistant = Assistant("Elena", 40)
    val consultant = Consultant("Artem", 50)
    val accountant = Accountant("Petr", 30)
    val list = listOf(director, assistant, consultant, accountant)
    for (worker in list) {
        if (worker is Cleaner) worker.clean()
        if (worker is Supplier) worker.buyThings()
    }

}