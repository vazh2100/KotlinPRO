package entities

// Для простоты реализации, просто используем готовые методы
class UnmodifiableList<T>(vararg array: T) : MyList<T> {

    private val elements = array

    override val size: Int
        get() = elements.size

    override fun get(index: Int): T = elements[index]

    override fun contains(element: T): Boolean = elements.contains(element)

    override fun iterator(): Iterator<T> = elements.iterator()

}