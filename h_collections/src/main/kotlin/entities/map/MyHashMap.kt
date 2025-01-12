package entities.map


import entities.collection.MyCollection
import entities.list.MyArrayList
import entities.set.MyHashSet
import entities.set.MySet
import kotlin.math.abs


class MyHashMap<K, V>(private val capacity: Int = DEFAULT_CAPACITY) : MyMutableMap<K, V> {


    private companion object {
        const val DEFAULT_CAPACITY = 16
        const val LOAD_FACTOR = 0.75
    }

    // Правильнее называть это хэш таблицей, а не массивом
    // Ячейки хэш таблицы называют корзинами или buckets
    private var elements = arrayOfNulls<Node<K, V>>(capacity)

    override var size: Int = 0
        private set

    override val keys: MySet<K>
        get() = MyKeySet<K>().apply { foreach { add(it.key) } }

    override val values: MyCollection<V>
        get() = MyArrayList<V>().apply { foreach { add(it.value) } }

    override fun get(key: K): V? {
        var current = elements[getElementPosition(key, elements.size)]
        while (current != null) {
            if (current.key == key) return current.value
            current = current.next
        }
        return null
    }

    override fun containsKey(key: K): Boolean {
        var current = elements[getElementPosition(key, elements.size)]
        while (current != null) {
            if (current.key == key) return true
            current = current.next
        }
        return false
    }

    override fun containsValue(value: V): Boolean {
        foreach { if (it.value == value) return true }
        return false
    }

    override fun put(key: K, value: V): V? {
        if (size >= elements.size * LOAD_FACTOR) increaseCapacity()
        return put(key, value, elements).also {
            if (it == null) {
                size++
            }
        }
    }


    override fun remove(key: K): V? {
        val position = getElementPosition(key, elements.size)
        val node = elements[position] ?: return null

        if (node.key == key) {
            elements[position] = node.next
            size--
            return node.value
        } else {
            var previous = node
            var current = node.next
            while (current != null) {
                if (current.key == key) {
                    previous.next = current.next
                    size--
                    return current.value
                } else {
                    previous = current
                    current = current.next
                }
            }
        }
        return null
    }


    override fun clear() {
        elements = arrayOfNulls(capacity)
        size = 0
    }


    private fun getElementPosition(key: K, size: Int): Int {
        return abs(key.hashCode() % size)
    }

    // функция inline, чтобы была возможность выходить из родительского метода
    private inline fun foreach(operation: (Node<K, V>) -> Unit) {
        for (element in elements) {
            var current = element
            while (current != null) {
                operation(current)
                current = current.next
            }
        }
    }

    private fun increaseCapacity() {
        val newSize = elements.size * 2
        val newArray = arrayOfNulls<Node<K, V>>(newSize)
        for (element in elements) {
            var current = element
            while (current != null) {
                put(current.key, current.value, newArray)
                current = current.next
            }
        }
        elements = newArray
    }

    private fun put(key: K, value: V, array: Array<Node<K, V>?>): V? {
        val newElement = Node(key, value)
        val position = getElementPosition(key, array.size)
        val currentElement = array[position]
        if (currentElement == null) {
            array[position] = newElement
            return null
        } else {
            var current = currentElement
            while (true) {
                if (current?.key == key) {
                    return current?.value.also {
                        current?.value = value
                    }
                } else {
                    if (current?.next == null) {
                        current?.next = newElement
                        return null
                    } else {
                        current = current.next
                    }
                }
            }

        }
    }

    override fun toString(): String {
        return "NumbersHashSet(elements=${elements.contentToString()})"
    }


    data class Node<K, V>(
        val key: K, var value: V
    ) {

        var next: Node<K, V>? = null

        override fun toString(): String {
            return "Node(key=$key, value=$value, next=$next)"
        }


    }

    inner class MyKeySet<K> : MyHashSet<K>() {
        override fun iterator(): MutableIterator<K> {
            return KeySetIterator()
        }
    }


    inner class KeySetIterator<K> : MutableIterator<K> {
        override fun hasNext(): Boolean {
            TODO("Not yet implemented")
        }

        override fun next(): K {
            TODO("Not yet implemented")
        }

        override fun remove() {
            TODO("Not yet implemented")
        }

    }
}


/// Технический долг ( сделать перед переходом к следующему разделу)
/// Во всех mutable iterator реализовать remove
/// Реализовать TreeSet с помощью красно-черного дерева
/// Реализовать LinkedHashMap
/// Реализовать TreeHashMap
/// в MyHashMap реализовать три типа итерации с запретом на модификацию