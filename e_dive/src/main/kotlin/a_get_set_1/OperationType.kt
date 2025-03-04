package a_get_set_1

enum class OperationType(val title: String) {
    EXIT("Выход"),
    REGISTER_NEW_ITEM("Внести товар"),
    SHOW_ALL_ITEMS("Показать весь товар"),
    REMOVE_ITEM("Удалить товар"),
    HIRE_WORKER("Нанять работника"),
    FIRE_WORKER("Уволить работника"),
    SHOW_ALL_WORKERS("Показать всех работников"),
    CHANGE_SALARY("Изменить зарплату")
}