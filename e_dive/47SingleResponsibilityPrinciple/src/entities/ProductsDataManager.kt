package entities

import java.io.File

class ProductsDataManager {

    private val productsFile = File("cards.txt")

    fun loadProducts(): MutableList<ProductCard> {
        if (!productsFile.exists()) productsFile.createNewFile()
        return productsFile.readLines().mapNotNull { if (it.isNotEmpty()) ProductCard.fromJson(it) else null }
            .toMutableList()
    }

    fun deleteProduct(name: String) {
        val cards = loadProducts()
        cards.removeIf { it.name == name }
        productsFile.writeText("")
        for (card in cards) {
            productsFile.appendText(card.toJson() + "\n")
        }
    }

    fun saveProduct(productCard: ProductCard) {
        productsFile.appendText(productCard.toJson() + "\n")
    }

}