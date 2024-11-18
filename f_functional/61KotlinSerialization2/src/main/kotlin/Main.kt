package com.vazh2100


import com.vazh2100.entities.PersonDataManager
import com.vazh2100.entities.entities.ProductCardDataManager


fun main() {
    PersonDataManager.profiles.forEach { println(it) }
    ProductCardDataManager.productCards.forEach { println(it) }
}