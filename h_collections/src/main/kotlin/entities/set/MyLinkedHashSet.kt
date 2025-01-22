package entities.set

import entities.collection.MyAbstractCollection
import kotlin.math.abs

@Suppress("TooManyFunctions")
class MyLinkedHashSet<T>(private val capacity: Int = DEFAULT_CAPACITY) : MyAbstractCollection<T>(), MyMutableSet<T> {

    private companion object {
        const val DEFAULT_CAPACITY = 16
        const val LOAD_FACTOR = 0.75
    }

    override var size: Int = 0
        private set

    private var elements = arrayOfNulls<Node<T>>(capacity)
    private var first: Node<T>? = null
    private var last: Node<T>? = null
    private var modCount = 0

    override fun add(element: T): Boolean {
        if (size >= elements.size * LOAD_FACTOR) increaseCapacity()
        return add(element, elements).also {
            if (it) {
                size++
                modCount++
            }
        }
    }

    override fun remove(element: T): Boolean {
        return tryRemove(element).also {
            if (it) {
                size--
                modCount++
            }
        }
    }

    override fun clear() {
        elements = arrayOfNulls(capacity)
        size = 0
        modCount++
    }

    override fun contains(element: T): Boolean {
        var current = elements[getElementPosition(element, elements.size)]
        while (current != null) {
            if (current.value == element) return true
            current = current.bucketNext
        }
        return false
    }

    override fun iterator(): MutableIterator<T> {
        return object : MutableIterator<T> {
            private var next = first
            private var elementToRemove: Node<T>? = null
            private val capturedModCount = modCount

            override fun hasNext(): Boolean {
                return next != null
            }

            override fun next(): T {
                if (capturedModCount != modCount) throw ConcurrentModificationException()
                return next!!.value.also {
                    elementToRemove = next
                    next = next?.next
                }
            }

            override fun remove() {
                if (elementToRemove == null) error("")
                remove(elementToRemove!!.value)
                elementToRemove = null
                modCount--
            }
        }
    }

    private fun increaseCapacity() {
        val newSize = elements.size * 2
        val newArray = arrayOfNulls<Node<T>>(newSize)
        last = null
        for (element in this) {
            add(element, newArray)
        }
        elements = newArray
    }

    @Suppress("NestedBlockDepth")
    private fun add(element: T, array: Array<Node<T>?>): Boolean {
        val newElement = Node(element)
        val position = getElementPosition(element, array.size)
        val currentElement = array[position]
        if (currentElement == null) {
            array[position] = newElement
            newElement.link(last, null)
            return true
        } else {
            var current = currentElement
            while (true) {
                if (current?.value == element) {
                    return false
                } else {
                    if (current?.bucketNext == null) {
                        current?.bucketNext = newElement
                        newElement.link(last, null)
                        return true
                    } else {
                        current = current.bucketNext
                    }
                }
            }
        }
    }

    private fun getElementPosition(element: T, size: Int): Int {
        return abs(element.hashCode() % size)
    }

    private fun tryRemove(element: T): Boolean {
        val position = getElementPosition(element, elements.size)
        val node = elements[position] ?: return false

        if (node.value == element) {
            elements[position] = node.bucketNext
            node.unlink()
            return true
        } else {
            var previous = node
            var current = node.bucketNext
            while (current != null) {
                if (current.value == element) {
                    previous.bucketNext = current.bucketNext
                    current.unlink()
                    return true
                } else {
                    previous = current
                    current = current.bucketNext
                }
            }
        }
        return false
    }

    private fun Node<T>.unlink() {
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

    private fun Node<T>.link(previous: Node<T>?, next: Node<T>?) {
        this.previous = previous
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

    class Node<T>(
        val value: T,
    ) {
        var bucketNext: Node<T>? = null
        var previous: Node<T>? = null
        var next: Node<T>? = null

        override fun toString(): String {
            return "Node(value=$value, previous=${previous?.value}, next=${next?.value}, bucketNext=$bucketNext)"
        }
    }
}
