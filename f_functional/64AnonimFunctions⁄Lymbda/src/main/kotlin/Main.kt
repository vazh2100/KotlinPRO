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
    println(filtered)

    val products = ProductCardDataManager.productCards
    val filtered2 =
        filter(products) { it.productName.startsWith("D") && it.productCategory == ProductCategory.CLOTHING && it.productRating > 4 }
    println(filtered2)

    ProductCardDataManager.productCards.filterMy { it.productName == "" }
}

//Функция, которая принимает в качестве параметра функцию или возвращает функцию, называется функция высшего порядка
fun <T : Any> filter(
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
fun <T : Any> List<T>.filterMy(
    filter: (T) -> Boolean
): List<T> {
    val filtered = mutableListOf<T>()
    for (item in this) {
        if (filter(item)) filtered.add(item)
    }
    return filtered
}