package com.vazh2100

import com.vazh2100.entities.Gender
import com.vazh2100.entities.Person
import com.vazh2100.entities.PersonDataManager
import com.vazh2100.entities.ProductCardDataManager
import com.vazh2100.entities.ProductCategory


fun main() {
    val persons = PersonDataManager.profiles

    //полные и сокращенные типы записи анонимных функций и лямбда выражений
//    val function: (Person) -> Boolean = { person: Person -> person.firstName.startsWith("A") }
//    val function2 = { person: Person -> person.firstName.startsWith("A") }
//    val function3: (Person) -> Boolean = { person -> person.firstName.startsWith("A") }
//    val function4: (Person) -> Boolean = { it.firstName.startsWith("A") }


    var filtered = filter(persons, { person: Person -> person.firstName.startsWith("A") })
    filtered = filter(persons, { person -> person.age in 25..30 })
    filtered = filter(persons, { it.gender == Gender.MALE })
    val names = filtered.transform { it.firstName }
    val ages = filtered.transform { it.age }
    val agedPersons = filtered.transform { it.copy(age = it.age + 1) }
    println(names)
    println(ages)
    println(agedPersons)

    val products = ProductCardDataManager.productCards
    val filtered2 =
        filter(products) { it.productCategory == ProductCategory.CLOTHING }.transform { it.copy(productPrice = it.productPrice * 2) }
            .transform {
                "${it.productName} - ${it.productPrice}"
            }

    println(filtered2)

    ProductCardDataManager.productCards.filter { it.productName == "" }
}


inline fun <K, T> List<K>.transform(transform: (K) -> T): List<T> {
    return this.map(transform)
}

//Функция, которая принимает в качестве параметра функцию или возвращает функцию, называется функция высшего порядка
inline fun <T : Any> filter(
    list: List<T>,
    filter: (T) -> Boolean
): List<T> {
    val filtered = mutableListOf<T>()
    for (item in list) {
        if (filter(item)) filtered.add(item)
    }
    return filtered
}


//функция расширение
inline fun <T : Any> List<T>.filterMy(
    filter: (T) -> Boolean
): List<T> {
    val filtered = mutableListOf<T>()
    for (item in this) {
        if (filter(item)) filtered.add(item)
    }
    return filtered
}