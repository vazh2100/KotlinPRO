import entities.MyHashSet


fun main() {
    val items = MyHashSet<Int>()

    repeat(10) {
        items.add(it * 2)
    }
    // Эта запись идентична
    for (item in items) {
        println(item)
    }
    // Этой записи
    val iterator = items.iterator()
    while (iterator.hasNext()) {
        val item = iterator.next()
        println(item)
    }

}