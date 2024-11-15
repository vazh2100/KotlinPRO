import entities.*



fun main() {
    val rectangle = Rectangle(10, 10, 0)
    println(rectangle.area)
    rectangle.height += 20
    println(rectangle.area)

    val person = Person("Andrey", "Vazhenin", 178, 82)
    println(person.fullName)
//    person.family = "Mojj"


}