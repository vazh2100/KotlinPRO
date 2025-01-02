package entities


import kotlin.math.abs

class NumbersHashSet(capacity: Int = DEFAULT_CAPACITY) : NumbersMutableSet {

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


    override fun remove(number: Int) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun contains(number: Int): Boolean {
        TODO("Not yet implemented")
    }

    private fun getElementPosition(number: Int, size: Int): Int {
        return abs(number % size)
    }

    private fun increaseCapacity() {
        println(elements.contentToString())
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

    data class Node(
        val value: Int,
    ) {
        var next: Node? = null

        override fun toString(): String {
            return "Node(value=$value, next=$next)"
        }
    }
}