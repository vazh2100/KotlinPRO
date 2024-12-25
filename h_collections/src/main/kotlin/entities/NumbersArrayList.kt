package entities

class NumbersArrayList : NumbersMutableList {

    private companion object {
        const val DEFAULT_SIZE = 10
    }

    private var array = arrayOfNulls<Int>(DEFAULT_SIZE) // выделяет в памяти 4*10 байт c 10 ячейками null
//    private val array = IntArray(10) // выделяет в памяти 4*10 байт c 10 ячейками 0


    override var size: Int = 0
        private set

    override fun add(number: Int) {
        if (array.size == size) {
            val newArray = arrayOfNulls<Int>(array.size * 2)
            for (index in array.indices) {
                newArray[index] = array[index]
            }
            array = newArray
        }
        array[size] = number
        size++
    }

    override fun insert(index: Int, number: Int) {
        if (index > size) error("Index out of bound")
        if (array.size == size) {
            val newArray = arrayOfNulls<Int>(array.size * 2)
            for (i in newArray.indices) {
                when (i) {
                    in 0 until index -> newArray[i] = array[i]
                    index -> newArray[i] = number
                    in (index + 1)..size -> newArray[i] = array[i - 1]
                    else -> break
                }
            }
            array = newArray
        } else {
            for (i in size downTo index + 1) {
                array[i] = array[i - 1]
            }
            array[index] = number
        }
        size++
    }

    override fun get(index: Int): Int {
        if (index < 0 || index >= size) error("Index out of bound")
        return array[index]!!
    }

    override fun removeAt(index: Int) {
        for (i in index..size - 2) {
            array[i] = array[i + 1]
        }
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
        array = arrayOfNulls(DEFAULT_SIZE)
        size = 0
    }

    override fun contains(number: Int): Boolean {
        for (i in 0 until size) {
            if (array[i] == number) return true
        }
        return false
    }

    override fun toString(): String {
        return array.joinToString(", ")
    }
}