package entities

class NumbersArrayList(private val capacity: Int = DEFAULT_CAPACITY) : NumbersMutableList {

    private companion object {
        const val DEFAULT_CAPACITY = 10
    }

    private var array = arrayOfNulls<Int>(capacity) // выделяет в памяти 4*10 байт c 10 ячейками null
//    private val array = IntArray(10) // выделяет в памяти 4*10 байт c 10 ячейками 0


    override var size: Int = 0
        private set

    override fun add(number: Int) {
        increaseCapacityIfNeeded()
        array[size] = number
        size++
    }

    override fun insert(index: Int, number: Int) {
        if (index > size) error("Index out of bound")
        increaseCapacityIfNeeded()
        System.arraycopy(array, index, array, index + 1, size - index)
        array[index] = number
        size++
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return array[index]!!
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        System.arraycopy(array, index + 1, array, index, size - index - 1)
        size--
        array[size] = null
    }

    override fun remove(number: Int) {
        for (i in 0 until size) {
            if (array[i] == number) {
                removeAt(i)
                return
            }
        }
    }

    override fun clear() {
        array = arrayOfNulls(capacity)
        size = 0
    }

    override fun contains(number: Int): Boolean {
        for (i in 0 until size) {
            if (array[i] == number) return true
        }
        return false
    }

    private fun increaseCapacityIfNeeded() {
        if (array.size == size) {
            array = array.copyInto(arrayOfNulls(array.size * 2), 0)
        }
    }

    private fun checkIndex(index: Int) {
        if (index < 0 || index >= size) error("Index out of bound")
    }
}