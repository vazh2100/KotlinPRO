package entities.map

import entities.collection.MyAbstractCollection
import entities.collection.MyMutableCollection
import entities.set.MyMutableSet
import kotlin.math.abs

@Suppress("TooManyFunctions")
class MyLinkedHashMap<K, V>(private val capacity: Int = DEFAULT_CAPACITY) : MyAbstractMap<K, V>(), MyMutableMap<K, V> {

    private companion object {
        const val DEFAULT_CAPACITY = 16
        const val LOAD_FACTOR = 0.75
    }

    override var size: Int = 0
        private set

    override val keys: MyMutableSet<K> by lazy { KeySet() }
    override val values: MyMutableCollection<V> by lazy { Values() }
    override val entries: MyMutableSet<MyMutableMap.MyMutableEntry<K, V>> by lazy { EntrySet() }

    var first: MyMutableMap.MyMutableEntry<K, V>? = null
        private set
    var last: MyMutableMap.MyMutableEntry<K, V>? = null
        private set

    // Правильнее называть это хэш таблицей, а не массивом
    // Ячейки хэш таблицы называют корзинами или buckets
    private var elements = arrayOfNulls<Node<K, V>>(capacity)
    private var modCount = 0

    override fun get(key: K): V? {
        var current = elements[getElementPosition(key, elements.size)]
        while (current != null) {
            if (current.key == key) return current.value
            current = current.bucketNext
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
        return tryRemove(key).also {
            if (it != null) {
                modCount++
                size--
            }
        }
    }

    override fun clear() {
        elements = arrayOfNulls(capacity)
        first = null
        last = null
        size = 0
    }

    private fun getElementPosition(key: K, size: Int): Int {
        return abs(key.hashCode() % size)
    }

    private fun increaseCapacity() {
        val newSize = elements.size * 2
        val newArray = arrayOfNulls<Node<K, V>>(newSize)
        last = null
        for (element in this) {
            put(element.key, element.value, newArray)
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
            newElement.link(last, null)
            return null
        } else {
            var current = currentElement
            while (true) {
                if (current?.key == key) {
                    return current?.value.also {
                        current?.value = value
                        current?.unlink()
                        current?.link(last, null)
                    }
                } else {
                    if (current?.bucketNext == null) {
                        current?.bucketNext = newElement
                        newElement.link(last, null)
                        return null
                    } else {
                        current = current.bucketNext
                    }
                }
            }
        }
    }

    private fun tryRemove(key: K): V? {
        val position = getElementPosition(key, elements.size)
        val node = elements[position] ?: return null

        if (node.key == key) {
            elements[position] = node.bucketNext
            node.unlink()
            return node.value
        } else {
            var previous = node
            var current = node.bucketNext
            while (current != null) {
                if (current.key == key) {
                    previous.bucketNext = current.bucketNext
                    current.unlink()
                    return current.value
                } else {
                    previous = current
                    current = current.bucketNext
                }
            }
        }
        return null
    }

    private fun Node<K, V>.link(previous: MyMutableMap.MyMutableEntry<K, V>?, next: Node<K, V>?) {
        this.previous = previous as Node?
        this.next = next
        previous?.next = this
        next?.previous = this
        if (previous == null) {
            first = this
        }
        if (next == null) {
            last = this
        }
    }

    private fun Node<K, V>.unlink() {
        val previous = this.previous
        val next = this.next
        previous?.next = next
        next?.previous = previous
        if (previous == null) {
            first = next
        }
        if (next == null) {
            last = previous
        }
    }

    private data class Node<K, V>(
        override val key: K,
        override var value: V
    ) : MyMutableMap.MyMutableEntry<K, V> {

        var bucketNext: Node<K, V>? = null
        var previous: Node<K, V>? = null
        var next: Node<K, V>? = null

        override fun setValue(newValue: V): V {
            return value.also { value = newValue }
        }

        override fun toString(): String {
            return "Node(key=$key, value=$value, next=$bucketNext)"
        }
    }

    private inner class KeySet : MyAbstractCollection<K>(), MyMutableSet<K> {
        override val size: Int
            get() = this@MyLinkedHashMap.size

        override fun contains(element: K): Boolean = containsKey(element)

        override fun clear() = this@MyLinkedHashMap.clear()

        override fun remove(element: K): Boolean = this@MyLinkedHashMap.remove(element) != null

        override fun iterator(): KeyIterator = KeyIterator()

        override fun add(element: K): Boolean = throw UnsupportedOperationException()
    }

    private inner class Values : MyAbstractCollection<V>(), MyMutableCollection<V> {
        override val size: Int
            get() = this@MyLinkedHashMap.size

        override fun contains(element: V): Boolean = containsValue(element)

        override fun clear() = this@MyLinkedHashMap.clear()

        override fun iterator(): ValueIterator = ValueIterator()

        override fun add(element: V): Boolean = throw UnsupportedOperationException()

        override fun remove(element: V): Boolean {
            for (entry in entries) {
                if (entry.value == element) {
                    return this@MyLinkedHashMap.remove(entry.key) != null
                }
            }
            return false
        }
    }

    private inner class EntrySet :
        MyAbstractCollection<MyMutableMap.MyMutableEntry<K, V>>(),
        MyMutableSet<MyMutableMap.MyMutableEntry<K, V>> {

        override val size: Int
            get() = this@MyLinkedHashMap.size

        override fun clear() = this@MyLinkedHashMap.clear()

        override fun remove(element: MyMutableMap.MyMutableEntry<K, V>): Boolean =
            this@MyLinkedHashMap.remove(element.key) != null

        override fun add(element: MyMutableMap.MyMutableEntry<K, V>): Boolean = put(element.key, element.value) == null

        override fun contains(element: MyMutableMap.MyMutableEntry<K, V>): Boolean =
            containsKey(element.key) && containsValue(element.value)

        override fun iterator(): MutableIterator<MyMutableMap.MyMutableEntry<K, V>> = EntryIterator()
    }

    private abstract inner class NodeIterator {
        private var next = first as Node?
        private var nodeToRemove: Node<K, V>? = null
        private val capturedModCount = modCount

        fun hasNext(): Boolean {
            return next != null
        }

        fun nextNode(): Node<K, V> {
            if (capturedModCount != modCount) throw ConcurrentModificationException()
            return next!!.also {
                nodeToRemove = next
                next = next?.next
            }
        }

        fun remove() {
            if (nodeToRemove == null) error("")
            remove(nodeToRemove!!.key)
            nodeToRemove = null
            modCount--
        }
    }

    private inner class KeyIterator : NodeIterator(), MutableIterator<K> {
        override fun next(): K = nextNode().key
    }

    private inner class ValueIterator : NodeIterator(), MutableIterator<V> {
        override fun next(): V = nextNode().value
    }

    private inner class EntryIterator : NodeIterator(), MutableIterator<Node<K, V>> {
        override fun next(): Node<K, V> = nextNode()
    }
}
