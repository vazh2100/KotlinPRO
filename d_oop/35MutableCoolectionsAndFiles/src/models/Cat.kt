package models

class Cat(val name: String) : CatsFamily() {
    fun playWithMouse() {
        println("Играю с мышкой")
    }
}