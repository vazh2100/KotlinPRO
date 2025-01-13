package entities.set


import entities.map.MyHashMap

//Если изменить изменяемый объект после того, как мы его положили в хэш таблицу, то мы его не удалим и не найдём, пока
// не изменится вместимость массива, то есть не перераспределяться элементы по новому массиву
// В оригинальной коллекции в лучшем случае все операции выполняются за константное время
// в худшем случае за время от логарифмической зависимости от размера
class MyHashSet<T>(capacity: Int = DEFAULT_CAPACITY) : MyMutableSet<T> {

    private companion object {
        const val DEFAULT_CAPACITY = 16
    }

    override val size: Int
        get() = map.size

    private val map = MyHashMap<T, Unit>(capacity)

    override fun contains(element: T): Boolean = map.containsKey(element)

    override fun add(element: T): Boolean = map.put(element, Unit) == null

    override fun remove(element: T): Boolean = map.remove(element) != null

    override fun clear() = map.clear()

    override fun iterator() = map.keyIterator

}