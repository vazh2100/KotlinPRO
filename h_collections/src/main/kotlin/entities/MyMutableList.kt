package entities

interface MyMutableList<T> {

    val size: Int

    fun add(element: T)
    operator fun plus(element: T) = add(element)

    fun insert(index: Int, element: T)

    operator fun get(index: Int): T

    fun removeAt(index: Int)

    fun remove(element: T)
    operator fun minus(element: T) = remove(element)

    fun clear()

    fun contains(element: T): Boolean

}