fun main() {
    val numbers = mutableListOf<Int>()

    while (true) {
        val num = readln().toInt()
        if (num != 0) numbers.add(num) else break
    }
    numbers.forEach(::println)
}