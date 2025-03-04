package q_control_work

class Assistant : Worker {

    constructor(name: String, age: Byte) : super(name, age, WorkerType.ASSISTANT)
    constructor(map: Map<String, String>) : super(map)

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