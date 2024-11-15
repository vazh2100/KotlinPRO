package models

class Assistant(name: String, age: Byte? = null) : Worker(name, age) {

    override fun work() {
        super.work()
        print("I am bringing coffee...\n")
    }

    fun bringCoffee(coffeeType: String = "Cappuccino"): String {
        println("$name встал/а")
        println("Подошёл к кофе машине")
        println("Нажал кнопку $coffeeType")
        println("Взял кофе типа $coffeeType")
        println("Отнёс кофе")
        return coffeeType
    }
}