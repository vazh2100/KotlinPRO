package com.vazh2100

import kotlinx.serialization.json.Json
import java.io.File
import kotlin.time.measureTime

//особенность map, что она не перебирает коллекцию, а получает значение в один шаг, это даёт очень большой буст к производительности
fun main() {
    val file = File("dictionary.json")
    val content = file
        .readText()
        .trim()
    val dictionary = Json.decodeFromString<Map<String, String>>(content)
    showDescription(dictionary)
}

fun showDescription(dictionary: Map<String, String>) {
    while (true) {
        println("Enter word or 0 to exit: ")
        val value = readln().lowercase()
        if (value == "0") break
        measureTime {
            println(dictionary[value] ?: "Нет такого слова")
        }.also {
            println(it)
        }

    }
}






