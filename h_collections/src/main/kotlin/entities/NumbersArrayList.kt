package entities

class NumbersArrayList : NumbersMutableList {

    private val array = arrayOfNulls<Int>(10) // выделяет в памяти 4*10 байт c 10 ячейками null
//    private val array = IntArray(10) // выделяет в памяти 4*10 байт c 10 ячейками 0


    override var size: Int = 0
        private set

    override fun add(number: Int) {
        array[size] = number
        size++
    }

    override fun get(index: Int): Int {
        return array[index]!!
    }
}