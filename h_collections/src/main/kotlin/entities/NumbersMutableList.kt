package entities

interface NumbersMutableList {

    val size: Int

    fun add(number: Int)

    fun insert(index: Int, number: Int)

    fun get(index: Int): Int

    fun removeAt(index: Int)

    fun remove(number: Int)

    fun clear()

    fun contains(number: Int): Boolean


}