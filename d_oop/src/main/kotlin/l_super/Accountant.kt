package l_super

import k_polymorphism.Worker

class Accountant(name: String, age: Byte? = null) : Worker(name, age) {

    override fun work() {
        val productCards = mutableListOf<ProductCard>()
        while (true) {
            print("Введите код операции: 1 - внести товар, 0 - выход: ")
            val operationCode: Int = readln().toInt()
            if (operationCode != 1) break
            registerNewItem()?.let { productCards.add(it) }
        }
        for (productCard in productCards) {
            productCard.printInfo()
        }
    }

    private fun registerNewItem(): ProductCard? {
        print("Введите тип товара: 1 - еда, 2 - обувь,  3 - бытовая техника: ")
        val productCardType = readln().toByte()

        if (productCardType !in 1.toByte()..3.toByte()) {
            println("Неправильный тип товара")
            return null
        }

        print("Введите название товара: ")
        val name = readln()
        print("Введите бренд товара: ")
        val brand = readln()
        print("Введите цену товара: ")
        val price = readln().toFloat()

        return when (productCardType) {
            1.b -> {
                print("Введите калорийность: ")
                val calories = readln().toShort()
                Food(name, brand, price, calories)
            }
            2.b -> {
                print("Введите размер обуви: ")
                val size = readln().toByte()
                Shoes(name, brand, price, size)
            }
            3.b -> {
                print("Введите мощность техники: ")
                val power = readln().toShort()
                Appliances(name, brand, price, power)
            }
            else -> null
        }
    }
}

val Int.b: Byte
    get() = this.toByte()