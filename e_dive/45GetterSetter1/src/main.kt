import entities.*



fun main() {
//    val accountant = Accountant("Alice", 30)
//    accountant.work()
    val person = Person("Vasiliy", 180, 70)
    person.age = 10
    println(person.age)
    person.age = 9
    println(person.age)
}