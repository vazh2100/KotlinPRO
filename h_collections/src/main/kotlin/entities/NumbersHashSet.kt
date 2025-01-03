package entities


import kotlin.math.abs

class NumbersHashSet(private val capacity: Int = DEFAULT_CAPACITY) : NumbersMutableSet {

    private companion object {
        const val DEFAULT_CAPACITY = 16
        const val LOAD_FACTOR = 0.75
    }

    private var elements = arrayOfNulls<Node>(capacity)

    override var size: Int = 0
        private set


    override fun add(number: Int): Boolean {
        if (size >= elements.size * LOAD_FACTOR) increaseCapacity()
        return add(number, elements).also { if (it) size++ }
    }


    override fun remove(number: Int): Boolean {
        val position = getElementPosition(number, elements.size)
        val element = elements[position] ?: return false

        if (element.value == number) {
            elements[position] = element.next
            size--
            return true
        } else {
            var previous = element
            var current = element.next
            while (current != null) {
                if (current.value == number) {
                    previous.next = current.next
                    size--
                    return true
                } else {
                    previous = current
                    current = current.next
                }
            }
        }

        return false
    }


    override fun clear() {
        elements = arrayOfNulls(capacity)
        size = 0
    }

    override fun contains(number: Int): Boolean {
        var current = elements[getElementPosition(number, elements.size)]
        while (current != null) {
            if (current.value == number) return true
            current = current.next
        }
        return false
    }

    private fun getElementPosition(number: Int, size: Int): Int {
        return abs(number % size)
    }

    private fun increaseCapacity() {
        val newSize = elements.size * 2
        val newArray = arrayOfNulls<Node>(newSize)
        for (element in elements) {
            var current = element
            while (current != null) {
                add(current.value, newArray)
                current = current.next
            }
        }
        elements = newArray
    }

    private fun add(number: Int, array: Array<Node?>): Boolean {
        val newElement = Node(number)
        val position = getElementPosition(number, array.size)
        val currentElement = array[position]
        if (currentElement == null) {
            array[position] = newElement
            return true
        } else {
            var current = currentElement
            while (true) {
                if (current?.value == number) return false
                else {
                    if (current?.next == null) {
                        current?.next = newElement
                        return true
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


    data class Node(
        val value: Int,
    ) {
        var next: Node? = null

        override fun toString(): String {
            return "Node(value=$value, next=$next)"
        }
    }
}