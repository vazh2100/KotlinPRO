import entities.NumbersHashSet

fun main() {
    val numbers = NumbersHashSet()

    repeat(100) {
        numbers.add(it)
    }
}