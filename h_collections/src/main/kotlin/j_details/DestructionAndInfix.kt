package j_details

fun main() {
    val dictionary = listOf(
        "Hello" to "Bonjour",
        "Thank you" to "Merci"
    )

    // Класс Pair распадается на 2 переменные
    for ((first, second) in dictionary) {
        println("$first - $second")
    }
}

data class MyPair<F, S>(val first: F, val second: S)

infix fun <F, S> F.to(second: S) = MyPair(this, second)
