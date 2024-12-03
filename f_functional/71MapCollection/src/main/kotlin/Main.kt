package com.vazh2100

import com.vazh2100.entities.Entry
import kotlinx.serialization.json.Json
import java.io.File

//особенность map, что она не перебирает коллекцию, а получает значение в один шаг, это даёт очень большой буст к производительности
fun main() {
    phoneBook()
    return
    val map = mutableMapOf("a" to "aaaa", "b" to "bbbb", Pair("c", "cccc"))
    println(map["d"])
    println(map?.get("a"))
    map["e"] = "eeeee"
    map.put("z", "zzzzz")
    for (entry in map) {
        println(entry)
    }
    val keys = map.keys
    val values = map.values
    return
    val file = File("dictionary.json")
    val content = file
        .readText()
        .trim()
    val dictionary = Json.decodeFromString<List<Entry>>(content)
    showDescription(dictionary)
}

fun showDescription(dictionary: List<Entry>) {
    while (true) {
        println("Enter word or 0 to exit: ")
        val value = readln().lowercase()
        if (value == "0") break
        dictionary
            .find { it.value == value }
            ?.let {
                println(it.description)
            } ?: println("Нет такого слова")
    }
}


fun phoneBook() {
    val phoneBook = mutableMapOf<String, String>("Andrey" to "+79515099586")

    println("Введите контакт (Имя)_(Телефон): ")
    val (key, value) = readln().split(" ")
    phoneBook[key] = value

    while (true) {
        println("Введите Имя или 0 для выхода: ")
        val key = readln()
        if (key == "0") break
        phoneBook[key]?.let {
            println(it)
        } ?: println("Нет такого контакта")
    }

}







