package models

enum class OperationType(val title: String) {
    EXIT("Выход"), REGISTER_NEW_ITEM("Внести товар"), SHOW_ALL_ITEMS("Показать весь товар"), REMOVE_ITEM("Удалить товар"),
}