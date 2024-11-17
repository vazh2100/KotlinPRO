package com.vazh2100

import com.vazh2100.entities.Book
import com.vazh2100.entities.Item
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
//    val itemsFile = File("items.json")
//    saveItems(file)
//    val items = loadItems(itemsFile)
//    items.forEach {
//        println(it)
//    }
    val booksFile = File("books.json")
//    saveBooks(booksFile)
    val items = loadBooks(booksFile)
    items.forEach {
        println(it)
    }
}

fun saveItems(file: File) {
    val items = listOf(
        Item(1, "name1"),
        Item(2, "name2"),
        Item(3, "name3"),
        Item(4, "name4"),
        Item(5, "name5"),
    )
    val content = Json.encodeToString(items)
    file.writeText(content)
}

fun loadItems(file: File): List<Item> {
    val content = file.readText().trim()
    return Json.decodeFromString<List<Item>>(content)
}

fun saveBooks(file: File) {
    val items = listOf(
        Book("name1", "author1", 1994),
        Book("name2", "author2", 1995),
        Book("name3", "author3", 1996),
        Book("name4", "author4", 1997),
        Book("name5", "author5", 1998),
    )
    val content = Json.encodeToString(items)
    file.writeText(content)
}

fun loadBooks(file: File): List<Book> {
    val content = file.readText().trim()
    return Json.decodeFromString<List<Book>>(content)
}