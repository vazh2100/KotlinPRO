package j_details

import kotlin.concurrent.thread

fun main() {
    executeAsync {
        println("Hello")
    }

    executeCommand({ println("Hello1") }, { println("Hello2") })
}

// Сделать non local return из одного потока в другой невозможно.
// Поэтому лямбда подчёркнута красным. У функции thread другой контекст выполнения.
// Добавляя cross inline, мы указываем, что лямбда выполняется в другом контексте,
// что позволяет сделать executeAsync inline. Но это накладывает на лямбду ограничение:
// запрещает лямбде не локальный выход
// запрещает внутри этой лямбды вызывать suspend функции
private inline fun executeAsync(crossinline command: () -> Unit) {
    thread {
        command()
    }
}

// noinline запрещает встраивать лямбда выражение.
// Это имеет смысл, если функция принимает больше чем одно лямбда выражение.
// Для чего это нужно? Например, если нужно передать эту функцию, как объект в другое место.
// Компилятор ругается если не пометить эту функцию noinline
private inline fun executeCommand(command1: () -> Unit, noinline command2: () -> Unit) {
    command1()
    someFunction(command2)
}

fun someFunction(someL: () -> Unit) {
    someL()
}
