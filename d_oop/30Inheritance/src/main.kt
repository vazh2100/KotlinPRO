import models.*


fun main() {
    val cat = Cat("Vasya")
    val lion = Lion(30)
    println(cat.name)
    println(cat.legsCount)
    println(lion.legsCount)
    println(lion.prideCount)
    cat.playWithMouse()

    val director = Director("Andrey", 30)
    val assistant = Assistant("Elena")
    val consultant = Consultant("Artem")
    director.getCoffee(assistant, "Americano")
    director.forceConsultantToWork(consultant)

}