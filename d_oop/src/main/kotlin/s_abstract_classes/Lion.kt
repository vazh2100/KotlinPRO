package s_abstract_classes

class Lion(val prideCount: Byte) : CatsFamily() {

    override fun eat() {
        println("Кушаю зебру.")
    }
}