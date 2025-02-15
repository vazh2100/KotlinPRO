package a_sequence

fun main() {
    var filterCount = 0
    var mapCount = 0
    sequence<Int> {
        println("Start generation")
        yield(1)
        println("Continue generation")
        repeat(10) {
            yield(2)
        }
    }.filter {
        filterCount++
        it % 2 == 0
    }.map {
        mapCount++
        it * 10
    }.forEach(::println)

    println("filter: $filterCount, map: $mapCount")
}

fun first() {
    var filterCount = 0
    var mapCount = 0
    generateSequence(0) { it + 1 }.filter {
        filterCount++
        it % 2 == 0
    }.map {
        mapCount++
        it * 10
    }.take(10).forEach(::println)

    println("filter: $filterCount, map: $mapCount")
}