package entities

interface NumbersMutableSet {

    val size: Int

    fun add(number: Int): Boolean

    fun remove(number: Int): Boolean

    fun clear()

    fun contains(number: Int): Boolean

}