import entities.Item
import java.util.*


fun main() {}

fun theory() {
    //Создают linked hash set
    setOf("a", "b") // неизменяемый
    mutableSetOf("a", "b") // изменяемый
    linkedSetOf("a", "b")  // изменяемый

    //Создаёт HashSet. HashSet является изменяемой коллекцией
    hashSetOf("").remove("")

    //Создаёт TreeSet. Она является изменяемой
    TreeSet<Item>()
    val tree = sortedSetOf<Item>()

    //toList создаёт новую копию, но делает её mutable, поэтому добавление возможно после приведения типов
    (mutableListOf<Int>(1, 2, 3).toList() as MutableList).add(1) // ошибки нет


    //listOf создаёт действительно не изменяемую коллекцию
    (listOf<Int>(1, 2, 3) as MutableList).add(1) // ошибка
}