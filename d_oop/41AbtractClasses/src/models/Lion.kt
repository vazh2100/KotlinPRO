package models

class Lion(val prideCount: Byte) : CatsFamily() {
    override fun eat() {
        println("Кушаю зебру.")
    }
}