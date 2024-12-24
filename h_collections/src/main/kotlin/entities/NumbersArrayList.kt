package entities

class NumbersArrayList : NumbersMutableList {

    private var array = arrayOfNulls<Int>(10) // выделяет в памяти 4*10 байт c 10 ячейками null
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

    override fun get(index: Int): Int {
        return array[index]!!
    }

    override fun removeAt(index: Int) {
        for (i in index..size - 2) {
            array[i] = array[i + 1]
        }
        size--
        array[size] = null
    }
}