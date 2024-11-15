import models.Cat
import models.Lion


interface Vasya {

    open fun vasya() {
        println("")
    }
}

class VasyaImpl : Vasya {


    override fun vasya() {
        return super.vasya()
    }

}

fun main() {
    val cat = Cat("")
    val lion = Lion(10)
    val animals = listOf(cat, lion)
    animals.forEach {
        it.eat()
    }
}