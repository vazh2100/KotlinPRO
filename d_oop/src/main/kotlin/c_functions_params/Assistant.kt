package c_functions_params

class Assistant {

    fun bringCoffee(count: Int, coffeeType: String) {
        repeat(count) {
            println("Встал")
            println("Подошёл к кофе машине")
            println("Нажал кнопку $coffeeType")
            println("Взял кофе типа $coffeeType")
            println("Отнёс кофе")
        }

    }
}