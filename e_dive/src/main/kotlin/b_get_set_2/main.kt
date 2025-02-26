package b_get_set_2

fun main() {
    val rectangle = Rectangle(10, 10, 0)
    println(rectangle.area)
    rectangle.height += 20
    println(rectangle.area)
    val person = Person("Andrey", "V", 178, 82)
    println(person.fullName)
}