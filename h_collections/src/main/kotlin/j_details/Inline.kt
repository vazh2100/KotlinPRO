package j_details

import entities.collection.myListOf
import entities.list.MyArrayList
import entities.list.MyList
import kotlin.concurrent.thread

// myFilter и myFilter 2 выглядят в Байт коде выглядят идентично, если не указать ключевое слово inline.
// То есть, анонимная функция трансформируется в объект анонимного класса функционального интерфейса
// И в том и другом случае нельзя выйти из родительского метода через return
// не inline функции каждый раз создаёт объект анонимного класса, что занимает память
// внутри не inline функции нельзя вызывать suspend функции, даже если они внутри области корутин
// внутри не inline функции нельзя узнать тип Обобщения
// inline функции позволяют всё это делать
// минус inline функции - в разрастании байт-кода, поэтому inline функциями должны быть короткими
// делать функции inline стоит в 2 случаях: 1) если принимает другую функцию и короткая
// 2) если нужно знать тип обобщённого типа во время выполнения
inline fun <T> Iterable<T>.myFilter(filter: (T) -> Boolean): MyList<T> {
    val filtered = MyArrayList<T>()
    for (item in this) {
        if (filter(item)) filtered.add(item)
    }
    return filtered
}

fun <T> Iterable<T>.myFilter2(filter: Condition<T>): MyList<T> {
    val filtered = MyArrayList<T>()
    for (item in this) {
        if (filter.isSuitable(item)) filtered.add(item)
    }
    return filtered
}

fun interface Condition<T> {
    fun isSuitable(item: T): Boolean
}

fun main() {
    val worker = Worker("")
    val programmer = Programmer("b")
    val director = Director("c")
    val list = myListOf(worker, programmer, director)

    list
        .myFilter {
            return
            it is Programmer
        }
        .map { it as Programmer }
        .forEach { it.writeCode() }

    list
        .myFilter2 {
            it is Programmer
        }
        .map { it as Programmer }
        .forEach { it.writeCode() }
}

fun main2() {
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
