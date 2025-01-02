package entities

interface NumbersMutableSet {

    val size: Int

    fun add(number: Int): Boolean
    operator fun plus(number: Int) = add(number)

    fun remove(number: Int)
    operator fun minus(number: Int) = remove(number)

    fun clear()

    fun contains(number: Int): Boolean

}