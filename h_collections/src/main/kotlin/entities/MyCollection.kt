package entities


interface MyCollection<T> : Iterable<T> {
    val size: Int

    fun contains(element: T): Boolean
}