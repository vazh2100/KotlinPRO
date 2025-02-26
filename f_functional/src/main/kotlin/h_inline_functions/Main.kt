package h_inline_functions

import b_kotlin_serialization_2.Gender
import b_kotlin_serialization_2.PersonDataManager
import b_kotlin_serialization_2.ProductCategory
import d_anon_classes.ProductCardDataManager

fun main() {
    val persons = PersonDataManager.profiles
        .filter { it.firstName.startsWith("A") }
        .filter { it.age in 25..30 }
        .filter { it.gender == Gender.MALE }
        .map { it.copy(age = it.age + 1) }
    println(persons)
    val products = ProductCardDataManager.productCards
        .filter { it.productCategory == ProductCategory.CLOTHING }
        .map { it.copy(productPrice = it.productPrice * 2) }
        .transform { "${it.productName} - ${it.productPrice}" }
    println(products)
}

//Если не указать ключевое слово inline, то будет создаваться объект, который хранит в себе функцию operation, по сути
//анонимный объект, реализующий интерфейс содержащий функцию operation.
//А если указать ключевое слово inline, то в процессе компиляции вместо вызова функции operation вставится код функции, которую
// передали в качестве параметра.
inline fun <K, T> Collection<K>.transform(operation: (K) -> T): List<T> {
    return this.map(operation)
}

//функция расширение
inline fun <T> Iterable<T>.filter(
    filter: (T) -> Boolean
): List<T> {
    val filtered = mutableListOf<T>()
    for (item in this) {
        if (filter(item)) filtered.add(item)
    }
    return filtered
}
//class MyCollection<T>(val list: List<T> = listOf<T>()) : Iterable<T> {
//    override fun iterator(): Iterator<T> {
//        return object : Iterator<T> {
//            var position = 0
//            override fun hasNext(): Boolean {
//                return position <= list.size - 1
//            }
//
//            override fun next(): T {
//                return list[position++]
//            }
//        }
//    }
//
//}
