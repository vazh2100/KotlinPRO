package entities

// Comparable, либо передать в функцию sortedSet Comparator
class Item(var number: Int) : Comparable<Item> {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Item) return false
        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        return number
    }

    override fun toString(): String {
        return "$number"
    }

    // если текущий больше, чем other, то 1
    // если текущий равен other, то 0
    // если текущий меньше, чем other, то -1
    override fun compareTo(other: Item): Int {
        return number.compareTo(other.number)
    }
}
