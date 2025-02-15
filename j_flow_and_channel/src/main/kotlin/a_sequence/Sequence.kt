package a_sequence

import kotlin.random.Random

fun main() {
    var filterCount = 0
    var mapCount = 0
    val list = mutableListOf<Int>().apply {
        repeat(1000) {
            add(Random.nextInt(1001))
        }
    }.asSequence()

    list.filter {
        filterCount++
        it % 2 == 0
    }.map {
        mapCount++
        it * 10
    }.take(10).forEach(::println)

    println("filter: $filterCount, map: $mapCount")
}