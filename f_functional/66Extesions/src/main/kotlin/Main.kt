package com.vazh2100

import com.vazh2100.entities.*


fun main() {
    val a = readln()
        .toInt()
    println(a.isPositive)
    println(a.isNegative())

    val persons = PersonDataManager.profiles
        .filter { it.firstName.startsWith("A") }
        .filter { it.age in 25..30 }
        .filter { it.gender == Gender.MALE }
        .transform { it.copy(age = it.age + 1) }
    println(persons)

    val products = ProductCardDataManager.productCards
        .filter { it.productCategory == ProductCategory.CLOTHING }
        .transform { it.copy(productPrice = it.productPrice * 2) }
        .transform { "${it.productName} - ${it.productPrice}" }
    println(products)
}

//будет преобразована в fun isNegative(int: Int):Boolean = int < 0
// поэтому не должно быть такой функции в файле, чтобы не было конфликта
fun Int.isNegative(): Boolean = this < 0

//геттер расширение
val Int.isPositive: Boolean
    get() = this > 0

inline fun <K, T> List<K>.transform(transform: (K) -> T): List<T> {
    return this.map(transform)
}


//функция расширение
inline fun <T : Any> List<T>.filter(
    filter: (T) -> Boolean
): List<T> {
    val filtered = mutableListOf<T>()
    for (item in this) {
        if (filter(item)) filtered.add(item)
    }
    return filtered
}
