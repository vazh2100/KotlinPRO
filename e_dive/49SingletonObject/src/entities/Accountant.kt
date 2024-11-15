package entities


class Accountant(name: String, age: Byte, salary: Int) : Worker(name, age, WorkerType.ACCOUNTANT, salary) {


    private val workersDataManager = WorkersDataManager
    private val productsDataManager = ProductsDataManager


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
        print("Введите имя работника: ")
        val name = readln()
        print("Введите возраст работника: ")
        val age = readln().toByte()
        print("Введите зарплату сотрудника: ")
        val salary = readln().toInt()
        val worker = when (workerType) {
            WorkerType.DIRECTOR -> Director(name, age, salary)
            WorkerType.ASSISTANT -> Assistant(name, age, salary)
            WorkerType.CONSULTANT -> Consultant(name, age, salary)
            WorkerType.ACCOUNTANT -> Accountant(name, age, salary)
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
}
