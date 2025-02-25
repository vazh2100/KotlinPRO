package s_abstract_classes

fun main() {
    val cat = Cat("")
    val lion = Lion(10)
    val animals = listOf(cat, lion)
    animals.forEach {
        it.eat()
    }
}