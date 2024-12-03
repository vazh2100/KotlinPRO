package com.vazh2100

import com.vazh2100.entities.PersonDataManager
import com.vazh2100.entities.ProductCardDataManager
import com.vazh2100.entities.ProductCategory
import com.vazh2100.extensions.Extensions.transform


fun main() {
    PersonDataManager.profiles
//        .filter { it.firstName.startsWith("A") }
//        .filter { it.age in 25..30 }
//        .filter { it.gender == Gender.MALE }
        .map { it.copy(age = it.age + 1) }
        .sortedBy { it.firstName }  //сортировать по какому-то признаку
        .sortedByDescending() { it.firstName }  //сортировать по какому-то признаку в обратном порядке
        .forEach { println(it.firstName) }
//        .all { it.gender == Gender.MALE } // проверяет, что все элементы коллекции соответствуют условию
//        .maxBy { it.age } //возвращает максимальное значение по какому-то признаку
//        .minBy { it.age } //возвращает минимальное значение по какому-то признаку
    ProductCardDataManager.productCards
        .filter { it.productCategory == ProductCategory.CLOTHING }
        .map { it.copy(productPrice = it.productPrice * 2) }
        .transform { "${it.productName} - ${it.productPrice}" }
        .forEach { println(it) }

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
