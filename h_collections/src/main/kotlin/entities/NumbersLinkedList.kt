package entities

class NumbersLinkedList : NumbersMutableList {

    private var first: Node? = null
    private var last: Node? = null

    override var size: Int = 0
        private set

    override fun add(number: Int) {
        Node(number).link(last, null)
    }


    override fun insert(index: Int, number: Int) {
        when (index) {
            !in 0..size -> error("Index out of bound")
            size -> add(number)
            0 -> Node(number).link(null, first)
            else -> {
                val previous = getNode(index - 1)
                val next = previous.next
                Node(number).link(previous, next)
            }
        }
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return getNode(index).value
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        getNode(index).unlink()
    }

    override fun remove(number: Int) {
        var current = first
        repeat(size) {
            if (current?.value == number) {
                current?.unlink()
                return
            } else {
                current = current?.next
            }
        }
    }


    override fun clear() {
        first = null
        last = null
        size = 0
    }

    override fun contains(number: Int): Boolean {
        var current = first
        repeat(size) {
            if (current?.value == number) {
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

    private fun getNode(index: Int): Node {
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

    private fun Node.unlink() {
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

    private fun Node.link(previous: Node?, next: Node?) {
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


    class Node(
        val value: Int
    ) {
        var previous: Node? = null
        var next: Node? = null
    }
}