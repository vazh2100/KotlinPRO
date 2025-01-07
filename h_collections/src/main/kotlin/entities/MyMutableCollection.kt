package entities

///Iterable находится на вершине иерархии коллекции
interface MyMutableCollection<T> : Iterable<T> {

    val size: Int

    fun add(element: T): Boolean
    operator fun plus(element: T) = add(element)

    fun remove(element: T): Boolean
    operator fun minus(element: T) = remove(element)

    fun clear()

    fun contains(element: T): Boolean

}