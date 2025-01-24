package entities.set

import entities.collection.MyAbstractCollection
import entities.map.MyLinkedHashMap

@Suppress("TooManyFunctions")
class MyLinkedHashSet<T>(capacity: Int = DEFAULT_CAPACITY) : MyAbstractCollection<T>(), MyMutableSet<T> {

    private companion object {
        const val DEFAULT_CAPACITY = 16
    }

    override val size: Int
        get() = map.size
    val first: T?
        get() = map.first?.key
    val last: T?
        get() = map.last?.key

    private val map = MyLinkedHashMap<T, Unit>(capacity)

    override fun add(element: T): Boolean = map.put(element, Unit) == null

    override fun remove(element: T): Boolean = map.remove(element) != null

    override fun clear() = map.clear()

    override fun contains(element: T): Boolean = map.containsKey(element)

    override fun iterator(): MutableIterator<T> = map.keys.iterator()
}
