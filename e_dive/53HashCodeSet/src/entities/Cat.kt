package entities

class Cat(val name: String) : CatsFamily() {
    fun playWithMouse() {
        println("Играю с мышкой")

    }


    override fun eat() {
        println("Кушаю мышь.")
    }
}