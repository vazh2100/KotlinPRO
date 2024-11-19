package com.vazh2100

import com.vazh2100.entities.Filter
import com.vazh2100.entities.Gender
import com.vazh2100.entities.Person
import com.vazh2100.entities.PersonDataManager
import com.vazh2100.entities.ProductCard
import com.vazh2100.entities.ProductCardDataManager
import com.vazh2100.entities.ProductCategory


fun main() {
    val persons = PersonDataManager.profiles
    val filtered = filter(persons, object : Filter<Person> {
        override fun suitable(item: Person): Boolean {
            return item.firstName.startsWith("A") && item.age in 25..30 && item.gender == Gender.MALE
        }
    })
    println(filtered)

    val products = ProductCardDataManager.productCards
    val filtered2 = filter(products, object : Filter<ProductCard> {
        override fun suitable(item: ProductCard): Boolean {
            return item.productName.startsWith("D") && item.productCategory == ProductCategory.CLOTHING && item.productRating > 4
        }
    })
    println(filtered2)
}

fun <T : Any> filter(
    list: List<T>,
    filter: Filter<T>
): List<T> {
    val filtered = mutableListOf<T>()
    for (item in list) {
        if (filter.suitable(item)) filtered.add(item)
    }
    return filtered
}