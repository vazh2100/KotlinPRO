package entities.set

import entities.collection.MyCollection

interface MySet<T> : MyCollection<T> {
    override val size: Int
    override fun contains(element: T): Boolean
}