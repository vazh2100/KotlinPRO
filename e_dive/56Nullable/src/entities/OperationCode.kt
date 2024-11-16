package entities

enum class OperationCode(val title: String) {
    EXIT("Выход"),
    ENTER_ITEM("Внести элемент"),
    PRINT_ITEMS("Вывести список элементов"),
}