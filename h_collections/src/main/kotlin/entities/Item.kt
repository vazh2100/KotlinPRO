package entities

class Item(var number: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Item) return false
        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        return number
    }
}