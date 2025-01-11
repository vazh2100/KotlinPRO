package entities

interface MyList<T> : MyCollection<T> {
    override val size: Int
    override fun contains(element: T): Boolean
    operator fun get(index: Int): T
}