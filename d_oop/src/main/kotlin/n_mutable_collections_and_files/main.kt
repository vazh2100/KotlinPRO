package n_mutable_collections_and_files

import java.io.File

fun main() {
    val accountant = Accountant("Petr", 30)
    accountant.work()
    //
    val file = File("test.txt")
    val operations = OperationCode.entries

    while (true) {
        print("Введите код операции:")
        for (operation in operations) {
            print(" ${operation.ordinal} - ${operation.title}")
            if (operation.ordinal < operations.size - 1) print(",") else print(": ")
        }
        val operationIndex = readln().toInt()
        val operation = operations[operationIndex]

        when (operation) {
            OperationCode.EXIT -> break
            OperationCode.ENTER_ITEM -> addItem(file)
            OperationCode.PRINT_ITEMS -> printItems(file)
        }

    }

}

fun addItem(file: File) {
    print("Введите дело: ")
    val task = readln()
    file.appendText(task + "\n")
}

fun printItems(file: File) {
    val lines = file.readLines()
    for ((index, line) in lines.withIndex()) println("$index - $line")
}