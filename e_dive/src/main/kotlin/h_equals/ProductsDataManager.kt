package h_equals

import a_get_set_1.ProductCard
import java.io.File

object ProductsDataManager {

    private val productsFile = File("cards.txt")
    private val _products = loadProducts()
    val products
        get() = _products.toList()

    private fun loadProducts(): MutableList<ProductCard> {
        if (!productsFile.exists()) productsFile.createNewFile()
        return productsFile.readLines().mapNotNull { if (it.isNotEmpty()) ProductCard.fromJson(it) else null }
            .toMutableList()
    }

    fun saveProducts() {
        val builder = StringBuilder()
        for (product in _products) {
            builder.append(product.toJson() + "\n")
        }
        productsFile.writeText(builder.toString())
    }

    fun saveProduct(productCard: ProductCard) {
        _products.add(productCard)
    }

    fun deleteProduct(name: String) {
        _products.removeIf { it.name == name }
    }
}