package entities

//Один из двух участников паттерна Command. Просто функция, которую нужно выполнить. Например, долгая
fun interface Command {
    operator fun invoke()
}