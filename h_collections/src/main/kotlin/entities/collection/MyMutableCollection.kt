package entities.collection

// /Iterable находится на вершине иерархии коллекции
interface MyMutableCollection<T> : MyCollection<T>, MutableIterable<T> {

    override val size: Int
    override fun contains(element: T): Boolean

    fun add(element: T): Boolean
    operator fun plus(element: T) = add(element)

    fun remove(element: T): Boolean
    operator fun minus(element: T) = remove(element)

    fun clear()
}
