package entities.set

import entities.PrintableTreeNode


// реализовать итератор
// реализовать добавление элемента
// реализовать уделаение элемента
// скрыть распечатку в абстрактный слой

class MyTreeSet<T : Comparable<T>> : MyMutableSet<T> {

    private var root: Node<T>? = null

    override fun iterator(): MutableIterator<T> {
        TODO("Not yet implemented")
    }

    override val size: Int = 0

    @Suppress("NestedBlockDepth")
    override fun add(element: T): Boolean {
        val newElement = Node(element)
        if (root == null) {
            root = newElement
            return true
        } else {
            var current = root
            while (current != null) {
                if (element == current.value) {
                    return false
                } else if (element > current.value) {
                    if (current.rightChild == null) {
                        current.rightChild = newElement
                        current.rightChild?.parent = current
                    } else {
                        current = current.rightChild
                    }
                } else {
                    if (current.leftChild == null) {
                        current.leftChild = newElement
                        current.leftChild?.parent = current
                    } else {
                        current = current.leftChild
                    }
                }
            }
            return true
        }
    }

    override fun remove(element: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun contains(element: T): Boolean {
        TODO("Not yet implemented")
    }

    fun printTree() {
        root?.printNode()
    }


    class Node<T>(override val value: T) : PrintableTreeNode<T>() {
        var parent: Node<T>? = null
        override var leftChild: Node<T>? = null
        override var rightChild: Node<T>? = null

    }
}
