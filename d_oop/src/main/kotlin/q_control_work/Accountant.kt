package q_control_work

import m_enum.ProductType
import o_serialization_deserialization.Appliances
import o_serialization_deserialization.Food
import o_serialization_deserialization.ProductCard
import o_serialization_deserialization.Shoes
import java.io.File

class Accountant : Worker { constructor(name: String, age: Byte) : super(name, age, WorkerType.ACCOUNTANT)
    constructor(map: Map<String, String>) : super(map)

    private val productsFile = File("cards.txt")
    private val workersFile = File("workers.txt")

    override fun work() {
        val operationTypes = OperationType.entries
        while (true) {
            print("Введите код операции:")
            for (type in operationTypes) {
                print("\n${type.ordinal} - ${type.title}")
                if (type.ordinal < operationTypes.size - 1) print(",") else print(": ")
            }
            val operationCode = operationTypes[readln().toInt()]
            when (operationCode) {
                OperationType.EXIT -> break
                OperationType.REGISTER_NEW_ITEM -> registerNewItem()
                OperationType.SHOW_ALL_ITEMS -> showAllItems()
                OperationType.REMOVE_ITEM -> removeItem()
                OperationType.HIRE_WORKER -> hireNewWorker()
                OperationType.FIRE_WORKER -> fireWorker()
                OperationType.SHOW_ALL_WORKERS -> showAllWorkers()
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
        }.also { productsFile.appendText(it.toJson() + "\n") }
    }

    private fun showAllItems() {
        val productCards = loadItemsFromFile()
        for (productCard in productCards) {
            println(productCard)
        }
    }

    private fun loadItemsFromFile(): MutableList<ProductCard> {
        if (!productsFile.exists()) productsFile.createNewFile()
        return productsFile
            .readLines()
            .mapNotNull { if (it.isNotEmpty()) ProductCard.fromJson(it) else null }
            .toMutableList()
    }

    private fun removeItem() {
        val cards = loadItemsFromFile()
        print("Введите название карточки для удаления: ")
        val name = readln()
        cards.removeIf { it.name == name }
        productsFile.writeText("")
        for (card in cards) {
            productsFile.appendText(card.toJson() + "\n")
        }
    }

    private fun hireNewWorker(): Worker? {
        val workerTypes = WorkerType.entries
        print("Введите тип работника:")
        for (type in workerTypes) {
            print(" ${type.ordinal} - ${type.title}")
            if (type.ordinal < workerTypes.size - 1) print(",") else print(": ")
        }
        val workerTypeIndex = readln().toInt()
        val workerType: WorkerType
        try {
            workerType = workerTypes[workerTypeIndex]
        } catch (e: Throwable) {
            println(e)
            println("Неправильный тип работника")
            return null
        }
        print("Введите имя работника: ")
        val name = readln()
        print("Введите возраст работника: ")
        val age = readln().toByte()

        return when (workerType) {
            WorkerType.DIRECTOR -> Director(name, age)
            WorkerType.ASSISTANT -> Assistant(name, age)
            WorkerType.CONSULTANT -> Consultant(name, age)
            WorkerType.ACCOUNTANT -> Accountant(name, age)
        }.also { workersFile.appendText(it.toJson() + "\n") }
    }

    private fun fireWorker() {
        val workers = loadWorkersFromFile()
        print("Введите номер работника для удаления: ")
        val id = readln().toInt()
        try {
            workers.removeAt(id)
            workersFile.writeText("")
            for (worker in workers) {
                workersFile.appendText(worker.toJson() + "\n")
            }
        } catch (_: Throwable) {
        }

    }

    private fun showAllWorkers() {
        val workers = loadWorkersFromFile()
        for ((index, worker) in workers.withIndex()) {
            println("$index. $worker")
        }
    }

    private fun loadWorkersFromFile(): MutableList<Worker> {
        if (!workersFile.exists()) workersFile.createNewFile()
        return workersFile.readLines().mapNotNull { if (it.isNotEmpty()) fromJson(it) else null }.toMutableList()
    }
}