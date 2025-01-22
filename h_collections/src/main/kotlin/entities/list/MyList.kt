package entities.list

import entities.collection.MyCollection

interface MyList<T> : MyCollection<T> {
    override val size: Int
    override fun contains(element: T): Boolean
    operator fun get(index: Int): T
}
