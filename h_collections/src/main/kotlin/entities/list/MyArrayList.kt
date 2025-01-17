package entities.list


// val a = T() создать экземпляр обобщённого типа нельзя
// arrayOfNulls<T>(capacity) - нельзя создать массив объектов обобщенного типа, так как нужно знать, сколько памяти
// занимает тип
class MyArrayList<T>(private val capacity: Int = DEFAULT_CAPACITY) : MyMutableList<T> {

    private companion object {
        const val DEFAULT_CAPACITY = 10
    }

    private var elements = arrayOfNulls<Any>(capacity)// выделяет в памяти 4*10 байт c 10 ячейками null

    //    private val array = IntArray(10) // выделяет в памяти 4*10 байт c 10 ячейками 0
    private var modCount = 0


    override var size: Int = 0
        private set


    override fun add(element: T): Boolean {
        increaseCapacityIfNeeded()
        elements[size] = element
        size++
        modCount++
        return true
    }

    override fun insert(index: Int, element: T) {
        if (index > size) error("Index out of bound")
        increaseCapacityIfNeeded()
        System.arraycopy(elements, index, elements, index + 1, size - index)
        elements[index] = element
        size++
        modCount++
    }

    @Suppress("UNCHECKED_CAST")
    override fun get(index: Int): T {
        checkIndex(index)
        return elements[index]!! as T
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        System.arraycopy(elements, index + 1, elements, index, size - index - 1)
        size--
        elements[size] = null
        modCount++
    }

    override fun remove(element: T): Boolean {
        for (i in 0 until size) {
            if (elements[i] == element) {
                removeAt(i)
                return true
            }
        }
        return false
    }

    override fun clear() {
        elements = arrayOfNulls(capacity)
        size = 0
        modCount++
    }

    override fun contains(element: T): Boolean {
        for (i in 0 until size) {
            if (elements[i] == element) return true
        }
        return false
    }

    override fun iterator(): MutableIterator<T> = ArrayListIterator()

    private fun increaseCapacityIfNeeded() {
        if (elements.size == size) {
            elements = elements.copyInto(arrayOfNulls(elements.size * 2), 0)
        }
    }

    private fun checkIndex(index: Int) {
        if (index < 0 || index >= size) error("Index out of bound")
    }

    // Правило для remove:
    // remove можно вызывать, только если вызван next
    // remove можно вызывать, только один раз после next
    // иначе бросается Illegal State Exception
    inner class ArrayListIterator<T> : MutableIterator<T> {
        private var index = 0
        private var indexToRemove = -1
        private val capturedModCount = modCount

        override fun hasNext(): Boolean {
            return index < size
        }

        @Suppress("UNCHECKED_CAST")
        override fun next(): T {
            if (capturedModCount != modCount) throw ConcurrentModificationException()
            indexToRemove = index
            return elements[index++] as T
        }

        override fun remove() {
            if (indexToRemove == -1) throw IllegalStateException()
            removeAt(indexToRemove)
            index = indexToRemove
            indexToRemove = -1
            modCount--
        }

    }
}