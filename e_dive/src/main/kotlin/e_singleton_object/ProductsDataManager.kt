package e_singleton_object

import a_get_set_1.ProductCard
import java.io.File

object ProductsDataManager {

    private val productsFile = File("cards.txt")
    val products = loadProducts()

    private fun loadProducts(): MutableList<ProductCard> {
        if (!productsFile.exists()) productsFile.createNewFile()
        return productsFile.readLines().mapNotNull { if (it.isNotEmpty()) ProductCard.fromJson(it) else null }
            .toMutableList()
    }

    fun saveProducts() {
        val builder = StringBuilder()
        for (product in products) {
            builder.append(product.toJson() + "\n")
        }
        productsFile.writeText(builder.toString())
    }

    fun saveProduct(productCard: ProductCard) {
        products.add(productCard)
    }

    fun deleteProduct(name: String) {
        products.removeIf { it.name == name }
    }
}