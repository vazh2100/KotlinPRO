package o_serialization_deserialization

import k_polymorphism.Worker
import m_enum.ProductType
import n_mutable_collections_and_files.OperationType
import java.io.File

class Accountant(name: String, age: Byte? = null) : Worker(name, age) {

    private val productCards = mutableListOf<ProductCard>()
    private val file = File("cards")

    override fun work() {
        val operationTypes = OperationType.entries
        while (true) {
            print("Введите код операции:")
            for (type in operationTypes) {
                print(" ${type.ordinal} - ${type.title}")
                if (type.ordinal < operationTypes.size - 1) print(",") else print(": ")
            }
            val operationCode = OperationType.entries[readln().toInt()]
            when (operationCode) {
                OperationType.EXIT -> break
                OperationType.REGISTER_NEW_ITEM -> registerNewItem()?.let {
                    file.appendText(it.toJson() + "\n")
                }
                OperationType.SHOW_ALL_ITEMS -> showAllItems()
            }
        }

    }

    private fun registerNewItem(): ProductCard? {
        val productTypes = ProductType.entries
        print("Введите тип товара:")
        for ((index, type) in productTypes.withIndex()) {
            print(" $index - ${type.title}")
            if (type.ordinal < productTypes.size - 1) print(",") else print(": ")
        }
        val productCardTypeIndex = readln().toInt()
        val productType: ProductType

        try {
            productType = productTypes[productCardTypeIndex]
        } catch (e: Throwable) {
            println(e)
            println("Неправильный тип товара")
            return null
        }

        print("Введите название товара: ")
        val name = readln()
        print("Введите бренд товара: ")
        val brand = readln()
        print("Введите цену товара: ")
        val price = readln().toFloat()

        return when (productType) {
            ProductType.FOOD -> {
                print("Введите калорийность: ")
                val calories = readln().toShort()
                Food(name, brand, price, calories)
            }
            ProductType.SHOES -> {
                print("Введите размер обуви: ")
                val size = readln().toByte()
                Shoes(name, brand, price, size)
            }
            ProductType.APPLIANCE -> {
                print("Введите мощность техники: ")
                val power = readln().toShort()
                Appliances(name, brand, price, power)
            }
        }
    }

    private fun showAllItems() {
        val productCardsStrings = file.readLines()
        for (productCardString in productCardsStrings) {
            productCards.add(ProductCard.fromJson(productCardString))
        }
        for (productCard in productCards) {
            println(productCard)
        }
    }
}