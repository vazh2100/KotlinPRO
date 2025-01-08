import entities.Item
import java.util.*
import kotlin.random.Random


fun main() {
    //Создают linked hash set
    setOf("a", "b") // неизменяемый
    mutableSetOf("a", "b") // изменяемый
    linkedSetOf("a", "b")  // изменяемый

    //Создаёт HashSet. HashSet является изменяемой коллекцией
    hashSetOf("").remove("")

    //Создаёт TreeSet. Она является изменяемой
    TreeSet<Item>()
    val tree = sortedSetOf<Item>()

    repeat(100) {
        tree.add(Item(Random.nextInt(100)))
    }
    for (element in tree) {
        println(element)
    }

}