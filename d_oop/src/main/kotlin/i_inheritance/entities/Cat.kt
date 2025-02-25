package i_inheritance.entities

class Cat(val name: String) : CatsFamily() {

    fun playWithMouse() {
        println("Играю с мышкой")
    }
}