package entities.set

import entities.collection.MyCollection

interface MySet<out T> : MyCollection<T> {
    override val size: Int
    override fun contains(element: @UnsafeVariance T): Boolean
}
