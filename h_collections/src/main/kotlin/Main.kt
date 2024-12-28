import kotlin.time.measureTime

fun main() {
    val numbers = mutableListOf<Int>()

    val a = measureTime {
        repeat(100_000_000) {
            numbers.add(it)
        }
    }

    val b = measureTime {
        repeat(100) {
            numbers.add(0, it)
        }
    }
    println(a)
    println(b)
}