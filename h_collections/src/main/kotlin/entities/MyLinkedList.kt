package entities

class MyLinkedList<T> : MyMutableList<T> {

    private var first: Node<T>? = null
    private var last: Node<T>? = null

    override var size: Int = 0
        private set


    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            private var next = first
            override fun hasNext(): Boolean {
                return next != null
            }

            override fun next(): T {
                return next!!.value.also { next = next?.next }
            }

        }
    }

    override fun add(element: T): Boolean {
        Node(element).link(last, null)
        return true
    }


    override fun insert(index: Int, element: T) {
        when (index) {
            !in 0..size -> error("Index out of bound")
            size -> add(element)
            0 -> Node(element).link(null, first)
            else -> {
                val previous = getNode(index - 1)
                val next = previous.next
                Node(element).link(previous, next)
            }
        }
    }

    override fun get(index: Int): T {
        checkIndex(index)
        return getNode(index).value
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        getNode(index).unlink()
    }

    override fun remove(element: T): Boolean {
        var current = first
        repeat(size) {
            if (current?.value == element) {
                current?.unlink()
                return true
            } else {
                current = current?.next
            }
        }
        return false
    }


    override fun clear() {
        first = null
        last = null
        size = 0
    }

    override fun contains(element: T): Boolean {
        var current = first
        repeat(size) {
            if (current?.value == element) {
                return true
            } else {
                current = current?.next
            }
        }
        return false
    }

    override fun toString(): String {
        return buildString {
            var current = first
            while (current != null) {
                append(current.value)
                append(", ")
                current = current.next
            }
        }
    }

    private fun checkIndex(index: Int) {
        if (index < 0 || index >= size) error("Index out of bound")
    }

    private fun getNode(index: Int): Node<T> {
        if (index == 0) return first!!
        if (index == size - 1) return last!!
        if (index < size / 2) {
            var node = first
            repeat(index) {
                node = node?.next
            }
            return node!!
        } else {
            var node = last
            repeat(size - 1 - index) {
                node = node?.previous
            }
            return node!!
        }

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
        size--
    }

    private fun Node<T>.link(previous: Node<T>?, next: Node<T>?) {
        this.next = next
        this.previous = previous
        previous?.next = this
        next?.previous = this
        if (previous == null) {
            first = this
        }
        if (next == null) {
            last = this
        }
        size++
    }


    class Node<T>(
        val value: T
    ) {
        var previous: Node<T>? = null
        var next: Node<T>? = null
    }
}