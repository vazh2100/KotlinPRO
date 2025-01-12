package entities.set

import entities.collection.MyMutableCollection

interface MyMutableSet<T> : MySet<T>, MyMutableCollection<T> {
    override val size: Int
    override fun contains(element: T): Boolean

    override fun add(element: T): Boolean
    override fun remove(element: T): Boolean
    override fun clear()

}