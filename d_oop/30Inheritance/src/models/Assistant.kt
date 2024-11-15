package models

class Assistant(name: String, age: Byte? = null) : Worker(name, age) {
    fun bringCoffee(coffeeType: String = "Cappuccino"): String {
        println("$name встал/а")
        println("Подошёл к кофе машине")
        println("Нажал кнопку $coffeeType")
        println("Взял кофе типа $coffeeType")
        println("Отнёс кофе")
        return coffeeType
    }
}