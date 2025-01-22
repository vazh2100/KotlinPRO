package entities.map

import entities.map.MyMutableMap.MyMutableEntry

abstract class MyAbstractMap<K, V> : MyMutableMap<K, V> {

    operator fun iterator(): MutableIterator<MyMutableEntry<K, V>> = entries.iterator()

    override fun toString(): String {
        return buildString {
            appendLine("{")
            for (item in this@MyAbstractMap) {
                append(item.key)
                append(" to ")
                append(item.value)
                appendLine(", ")
            }
            appendLine("}")
        }
    }
}
