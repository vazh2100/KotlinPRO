package m_enum

import k_polymorphism.Worker
import l_super.Appliances
import l_super.Food
import l_super.ProductCard
import l_super.Shoes

class Accountant(name: String, age: Byte? = null) : Worker(name, age) {

    override fun work() {
        val productCards = mutableListOf<ProductCard>()
        val operationTypes = OperationType.entries
        while (true) {
            print("Введите код операции:")
            for (type in operationTypes) {
                print(" ${type.ordinal} - ${type.title}")
                if (type.ordinal < operationTypes.size - 1) print(",") else print(": ")
            }
            val operationCode = OperationType.entries[readln().toInt()]
            if (operationCode != OperationType.REGISTER_NEW_ITEM) break
            registerNewItem()?.let { productCards.add(it) }
        }
        for (productCard in productCards) {
            productCard.printInfo()
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
}