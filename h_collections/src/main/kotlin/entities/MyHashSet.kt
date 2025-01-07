package entities


import kotlin.math.abs

//Если изменить изменяемый объект после того, как мы его положили в хэш таблицу, то мы его не удалим и не найдём, пока
// не изменится вместимость массива, то есть не перераспределяться элементы по новому массиву
// В оригинальной коллекции в лучшем случае все операции выполняются за константное время
// в худшем случае за время от логарифмической зависимости от размера
class MyHashSet<T>(private val capacity: Int = DEFAULT_CAPACITY) : MyMutableSet<T> {

    private companion object {
        const val DEFAULT_CAPACITY = 16
        const val LOAD_FACTOR = 0.75
    }

    // Правильнее называть это хэш таблицей, а не массивом
    // Ячейки хэш таблицы называют корзинами или buckets
    private var elements = arrayOfNulls<Node<T>>(capacity)

    override var size: Int = 0
        private set


    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            private var index = 0
            private var foundElements = 0
            private var currentNode = elements[index]
            override fun hasNext(): Boolean {
                return foundElements < size
            }

            override fun next(): T {
                while (currentNode == null) {
                    currentNode = elements[++index]
                }
                return currentNode!!.value.also {
                    currentNode = currentNode?.next
                    foundElements++
                }
            }
        }
    }


    override fun add(element: T): Boolean {
        if (size >= elements.size * LOAD_FACTOR) increaseCapacity()
        return add(element, elements).also { if (it) size++ }
    }


    override fun remove(element: T): Boolean {
        val position = getElementPosition(element, elements.size)
        val node = elements[position] ?: return false

        if (node.value == element) {
            elements[position] = node.next
            size--
            return true
        } else {
            var previous = node
            var current = node.next
            while (current != null) {
                if (current.value == element) {
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

    override fun contains(element: T): Boolean {
        var current = elements[getElementPosition(element, elements.size)]
        while (current != null) {
            if (current.value == element) return true
            current = current.next
        }
        return false
    }

    private fun getElementPosition(element: T, size: Int): Int {
        return abs(element.hashCode() % size)
    }

    private fun increaseCapacity() {
        val newSize = elements.size * 2
        val newArray = arrayOfNulls<Node<T>>(newSize)
        for (element in elements) {
            var current = element
            while (current != null) {
                add(current.value, newArray)
                current = current.next
            }
        }
        elements = newArray
    }

    private fun add(element: T, array: Array<Node<T>?>): Boolean {
        val newElement = Node(element)
        val position = getElementPosition(element, array.size)
        val currentElement = array[position]
        if (currentElement == null) {
            array[position] = newElement
            return true
        } else {
            var current = currentElement
            while (true) {
                if (current?.value == element) return false
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


    data class Node<T>(
        val value: T,
    ) {
        var next: Node<T>? = null

        override fun toString(): String {
            return "Node(value=$value, next=$next)"
        }
    }
}