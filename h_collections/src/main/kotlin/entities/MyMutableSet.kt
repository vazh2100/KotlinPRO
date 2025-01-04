package entities

interface MyMutableSet<T> {

    val size: Int

    fun add(element: T): Boolean

    fun remove(element: T): Boolean

    fun clear()

    fun contains(element: T): Boolean

}