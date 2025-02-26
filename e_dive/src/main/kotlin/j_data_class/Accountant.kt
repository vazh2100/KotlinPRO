package j_data_class

import a_get_set_1.ProductType
import a_get_set_1.WorkerType
import g_copy.OperationType

data class Accountant(
    override val id: Int,
    override val name: String,
    override val age: Byte,
    override val salary: Int,
) : Worker(id, name, age, salary, WorkerType.ACCOUNTANT) {

    private val workersDataManager = WorkersDataManager
    private val productsDataManager = ProductsDataManager

    ///Можно иметь абстрактную версию метода, только с другим количеством параметров, но такую копию нужно обязательно
    //переопределять в потомках
    //нельзя иметь в родителе или здесь метода copy с такими же параметрами, если класс data
    override fun copy(
        age: Byte,
        salary: Int
    ): Worker {
        return Accountant(this.id, this.name, age, salary)
    }

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
                OperationType.EXIT -> {
                    workersDataManager.saveWorkers()
                    productsDataManager.saveProducts()
                    break
                }
                OperationType.REGISTER_NEW_ITEM -> registerNewItem()
                OperationType.SHOW_ALL_ITEMS -> showAllItems()
                OperationType.REMOVE_ITEM -> removeProduct()
                OperationType.HIRE_WORKER -> hireNewWorker()
                OperationType.FIRE_WORKER -> fireWorker()
                OperationType.SHOW_ALL_WORKERS -> showAllWorkers()
                OperationType.CHANGE_SALARY -> changeSalary()
                OperationType.CHANGE_AGE -> changeAge()
            }
        }

    }

    private fun registerNewItem() {
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
            return
        }

        print("Введите название товара: ")
        val name = readln()
        print("Введите бренд товара: ")
        val brand = readln()
        print("Введите цену товара: ")
        val price = readln().toFloat()
        val product = when (productType) {
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
        productsDataManager.saveProduct(product)

    }

    private fun removeProduct() {
        print("Введите название карточки для удаления: ")
        val name = readln()
        productsDataManager.deleteProduct(name)
    }

    private fun showAllItems() {
        val productCards = productsDataManager.products
        for (productCard in productCards) {
            println(productCard)
        }
    }

    private fun hireNewWorker() {
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
            println("Неправильный тип работника")
            return
        }
        print("Введите ID сотрудника: ")
        val id = readln().toInt()
        print("Введите имя работника: ")
        val name = readln()
        print("Введите возраст работника: ")
        val age = readln().toByte()
        print("Введите зарплату сотрудника: ")
        val salary = readln().toInt()
        val worker = when (workerType) {
            WorkerType.DIRECTOR -> Director(id, name, age, salary)
            WorkerType.ASSISTANT -> Assistant(id, name, age, salary)
            WorkerType.CONSULTANT -> Consultant(id, name, age, salary)
            WorkerType.ACCOUNTANT -> Accountant(id, name, age, salary)
        }
        workersDataManager.saveWorker(worker)
    }

    private fun fireWorker() {
        print("Введите номер работника для удаления: ")
        val id = readln().toInt()
        workersDataManager.deleteWorker(id)
    }

    private fun showAllWorkers() {
        val workers = workersDataManager.workers
        for ((index, worker) in workers.withIndex()) {
            println("$index. $worker")
        }
    }

    private fun changeSalary() {
        print("Введите номер работника для изменения зарплаты: ")
        val id = readln().toInt()
        print("Введите новую зарплату: ")
        val salary = readln().toInt()
        workersDataManager.changeWorkerSalary(id, salary)
    }

    private fun changeAge() {
        print("Введите номер работника для изменения возраста: ")
        val id = readln().toInt()
        print("Введите новый возраст: ")
        val age = readln().toByte()
        workersDataManager.changeWorkerAge(id, age)
    }

    companion object {

        fun fromJson(map: Map<String, String>): Accountant {
            val name = map["name"]!!
            val age = map["age"]!!.toByte()
            val salary = map["salary"]!!.toInt()
            val id = map["id"]!!.toInt()
            return Accountant(id, name, age, salary)
        }
    }

}
