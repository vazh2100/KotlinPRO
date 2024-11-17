package entities

class Test(var number: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Test

        return number == other.number
    }
}