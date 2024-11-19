package com.vazh2100.entities.entities

import com.vazh2100.entities.ProductCard
import kotlinx.serialization.json.Json
import java.io.File

object ProductCardDataManager {
    private val productCardFile = File("product_cards.json")


    private val _productCards: MutableList<ProductCard> = loadProducts()
    val productCards get() = _productCards.toList()


    private fun loadProducts(): MutableList<ProductCard> {
        val content = productCardFile.readText().trim()
        return Json.decodeFromString(content)
    }
}