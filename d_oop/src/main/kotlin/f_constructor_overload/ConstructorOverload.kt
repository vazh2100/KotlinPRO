package f_constructor_overload

fun main() {
    val rectangle = Rectangle(5, 10)
    rectangle.draw()
    println("-")
    val rectangle2 = Rectangle(10)
    rectangle2.draw()
    println("-")
    val rectangle3 = Rectangle()
    rectangle3.draw()
}