import entities.Item
import entities.MyHashSet

fun main() {
    val items = MyHashSet<Item>()
    val item = Item(10)
    items.add(item)
    println(items.contains(item))
    item.number = 9
    println(items.contains(item))
}