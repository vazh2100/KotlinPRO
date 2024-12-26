package entities

interface NumbersMutableList {

    val size: Int

    fun add(number: Int)
    operator fun plus(number: Int) = add(number)

    fun insert(index: Int, number: Int)

    operator fun get(index: Int): Int

    fun removeAt(index: Int)

    fun remove(number: Int)
    operator fun minus(number: Int) = remove(number)

    fun clear()

    fun contains(number: Int): Boolean


}