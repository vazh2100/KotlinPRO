package j_details

import entities.collection.myListOf
import entities.list.MyArrayList
import entities.list.MyList

open class Worker(val name: String)

class Programmer(name: String) : Worker(name) {
    fun writeCode() {
        println("Writing")
    }
}

class Director(name: String) : Worker(name)

// Theory
// Вариантность - сохранение совместимости присваивания исходных типов у производных типов.
// Исходные типы - это Worker и Programmer, а производные типы - это List<Worker> и List<Programmer>
// Инвариантность - отсутствие совместимости у производных типов. То, что мы не можем передать коллекцию
// подтипа туда, где принимается коллекция родительского типа следствием инвариантности.
// Ковариантность - наличие совместимости у производных типов прямого типа(тип T и его потомки).
// Чтобы была ковариантность, нужно указать ключевое слово out в производном типе.
// В Котлин и Java изменяемые коллекции инвариативные, а неизменяемые ковариативные.
// Изменяемые коллекции таким образом защищены от такой ситуации:
// fun main() {
//    val list: MyMutableList<Programmer> = MyArrayList()
//    val list2: MyMutableList<Any> = list
//    list2.add("")
//    for (element in list) {  здесь компилятор считает, что имеет дело с Programmer
//        element.name // Здесь возникает ошибка времени выполнения
//    }
// }

data class Container<T>(var value: T? = null)

// fun main() {
//    val worker = Container<Worker>()
//    val programmer = Container(Programmer("b"))
//    val director = Container(Director("c"))
//    copy(programmer, worker)
//    copy(director, worker)
//    println(worker.value)
//    println(programmer.value)
//    println(director.value)
//
//
// }

// Контр вариантность - наличие совместимости у производных типов обратного направления(тип T и его родители).
// Чтобы была контр вариантность, нужно указать ключевое слово in в производном типе
fun <T> copy(src: Container<T>, dst: Container<in T>) {
    dst.value = src.value
}

// out - producer, отдаёт данные наружу
// in - consumer, может только принимать данные
// producer - extends, consumer - super

// Обобщённые типы придуманы для программистов
// В момент работы программы обобщённых типов не существует
// После компиляции обобщённый тип приводится к типу Object(=Any) и в определённый местах программы
// согласно исходному коду производится downcast
// Разницы между использованием val: Any? и val:T? для компилятора нет
// Поэтому следующий код помечен ошибкой: Cannot check for instance of erased type: Container<Int>
// В момент компиляции произошло стирание типов
// fun <T> container(container: Container<T>) {
//     if (container is Container<Int>)
// }

// fun main() {
//    val worker = Worker("")
//    val programmer = Programmer("b")
//    val director = Director("c")
//    val list = myListOf(worker, programmer, director)
//
// //    list.filter { it is Programmer }.map { it as Programmer }.forEach { it.writeCode() }
//    list
//        .myFilterIsInstance<Programmer>()
//        .forEach { it.writeCode() }
// }

// "reified" позволяет не стирать тип во время компиляции, но такое возможно только в inline функции,
// то есть функции, тело которой будет вставлено в код вместо её вызова.
// * - это Star Projection. Означает любой тип. Позволяет упростить типизацию, если тип нигде
// не используется в дальнейшем. * = out Any?
inline fun <reified R> Iterable<*>.myFilterIsInstance(): MyList<R> {
    val result = MyArrayList<R>()
    for (element in this) {
        if (element is R) result.add(element)
    }
    return result
}

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
