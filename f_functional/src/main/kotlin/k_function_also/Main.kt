package k_function_also

import b_kotlin_serialization_2.Gender
import b_kotlin_serialization_2.PersonDataManager

fun main() {
    PersonDataManager.profiles
        .also { println("Filtering") }
        .filter { it.firstName.startsWith("A") }
        .filter { it.age in 25..30 }
        .filter { it.gender == Gender.MALE }
        .also { println("Map") }
        .map { it.copy(age = it.age + 1) }
        .also { println("Sorting") }
        .sortedBy { it.firstName }  //сортировать по какому-то признаку
        .also { println("Printing") }
        .forEach { println(it.firstName) }
//    ProductCardDataManager.productCards
//        .filter { it.productCategory == ProductCategory.CLOTHING }
//        .map { it.copy(productPrice = it.productPrice * 2) }
//        .transform { "${it.productName} - ${it.productPrice}" }
//        .forEach { println(it) }

}








