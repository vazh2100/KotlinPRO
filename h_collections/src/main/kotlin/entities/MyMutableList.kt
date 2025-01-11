package entities

interface MyMutableList<T> : MyList<T>, MyMutableCollection<T> {
    override val size: Int
    override fun contains(element: T): Boolean
    override operator fun get(index: Int): T

    override fun add(element: T): Boolean
    override fun remove(element: T): Boolean
    override fun clear()

    fun insert(index: Int, element: T)
    fun removeAt(index: Int)
}