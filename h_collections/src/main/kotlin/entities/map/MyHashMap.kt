package entities.map

import entities.collection.MyAbstractCollection
import entities.collection.MyMutableCollection
import entities.set.MyMutableSet
import kotlin.math.abs

class MyHashMap<K, V>(private val capacity: Int = DEFAULT_CAPACITY) : MyAbstractMap<K, V>(), MyMutableMap<K, V> {

    private companion object {
        const val DEFAULT_CAPACITY = 16
        const val LOAD_FACTOR = 0.75
    }

    override var size: Int = 0
        private set

    override val keys: MyMutableSet<K> by lazy { KeySet() }
    override val values: MyMutableCollection<V> by lazy { Values() }
    override val entries: MyMutableSet<MyMutableMap.MyMutableEntry<K, V>> by lazy { EntrySet() }

    // Правильнее называть это хэш таблицей, а не массивом
    // Ячейки хэш таблицы называют корзинами или buckets
    private var elements = arrayOfNulls<Node<K, V>>(capacity)
    private var modCount = 0

    override fun get(key: K): V? {
        var current = elements[getElementPosition(key, elements.size)]
        while (current != null) {
            if (current.key == key) return current.value
            current = current.next
        }
        return null
    }

    override fun containsKey(key: K): Boolean {
        return get(key) != null
    }

    override fun containsValue(value: V): Boolean {
        for (v in values) {
            if (v == value) return true
        }
        return false
    }

    override fun put(key: K, value: V): V? {
        if (size >= elements.size * LOAD_FACTOR) increaseCapacity()
        return put(key, value, elements).also {
            if (it == null) {
                modCount++
                size++
            }
        }
    }

    override fun remove(key: K): V? {
        return remove(key, Unit).also {
            if (it != null) {
                modCount++
                size--
            }
        }
    }

    override fun clear() {
        elements = arrayOfNulls(capacity)
        size = 0
    }

    private fun getElementPosition(key: K, size: Int): Int {
        return abs(key.hashCode() % size)
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

    @Suppress("NestedBlockDepth")
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

    private fun remove(key: K, unit: Unit): V? {
        unit.hashCode()
        val position = getElementPosition(key, elements.size)
        val node = elements[position] ?: return null

        if (node.key == key) {
            elements[position] = node.next
            return node.value
        } else {
            var previous = node
            var current = node.next
            while (current != null) {
                if (current.key == key) {
                    previous.next = current.next
                    return current.value
                } else {
                    previous = current
                    current = current.next
                }
            }
        }
        return null
    }

    data class Node<K, V>(
        override val key: K,
        override var value: V
    ) : MyMutableMap.MyMutableEntry<K, V> {

        var next: Node<K, V>? = null

        override fun setValue(newValue: V): V {
            return value.also { value = newValue }
        }

        override fun toString(): String {
            return "Node(key=$key, value=$value, next=$next)"
        }
    }

    inner class KeySet : MyAbstractCollection<K>(), MyMutableSet<K> {
        override val size: Int
            get() = this@MyHashMap.size

        override fun contains(element: K): Boolean = containsKey(element)

        override fun clear() = this@MyHashMap.clear()

        override fun remove(element: K): Boolean = this@MyHashMap.remove(element) != null

        override fun iterator(): KeyIterator = KeyIterator()

        override fun add(element: K): Boolean = throw UnsupportedOperationException()
    }

    inner class Values : MyAbstractCollection<V>(), MyMutableCollection<V> {

        override val size: Int
            get() = this@MyHashMap.size

        override fun contains(element: V): Boolean = containsValue(element)

        override fun clear() = this@MyHashMap.clear()

        override fun iterator(): ValueIterator = ValueIterator()

        override fun add(element: V): Boolean = throw UnsupportedOperationException()

        override fun remove(element: V): Boolean {
            for (entry in entries) {
                if (entry.value == element) {
                    return this@MyHashMap.remove(entry.key) != null
                }
            }
            return false
        }
    }

    inner class EntrySet :
        MyAbstractCollection<MyMutableMap.MyMutableEntry<K, V>>(),
        MyMutableSet<MyMutableMap.MyMutableEntry<K, V>> {

        override val size: Int
            get() = this@MyHashMap.size

        override fun clear() = this@MyHashMap.clear()

        override fun remove(element: MyMutableMap.MyMutableEntry<K, V>): Boolean =
            this@MyHashMap.remove(element.key) != null

        override fun add(element: MyMutableMap.MyMutableEntry<K, V>): Boolean = put(element.key, element.value) == null

        override fun contains(element: MyMutableMap.MyMutableEntry<K, V>): Boolean =
            containsKey(element.key) && containsValue(element.value)

        override fun iterator(): MutableIterator<MyMutableMap.MyMutableEntry<K, V>> = EntryIterator()
    }

    abstract inner class NodeIterator {
        private var index = 0
        private var foundElements = 0
        private var currentNode = elements[index]
        private var nodeToDelete: Node<K, V>? = null
        private val capturedModCount = modCount
        fun hasNext(): Boolean {
            return foundElements < size
        }

        fun nextNode(): Node<K, V> {
            if (capturedModCount != modCount) throw ConcurrentModificationException()
            while (currentNode == null) {
                currentNode = elements[++index]
            }
            return currentNode!!.also {
                nodeToDelete = currentNode
                currentNode = currentNode?.next
                foundElements++
            }
        }

        fun remove() {
            if (nodeToDelete == null) error("")
            remove(nodeToDelete!!.key)
            nodeToDelete = null
            modCount--
        }
    }

    inner class KeyIterator : NodeIterator(), MutableIterator<K> {
        override fun next(): K = nextNode().key
    }

    inner class ValueIterator : NodeIterator(), MutableIterator<V> {
        override fun next(): V = nextNode().value
    }

    inner class EntryIterator : NodeIterator(), MutableIterator<Node<K, V>> {
        override fun next(): Node<K, V> = nextNode()
    }
}
