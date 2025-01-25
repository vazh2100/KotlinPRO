package entities.list

import entities.collection.MyCollection

interface MyList<out T> : MyCollection<T> {
    override val size: Int
    override fun contains(element: @UnsafeVariance T): Boolean
    operator fun get(index: Int): T
}
