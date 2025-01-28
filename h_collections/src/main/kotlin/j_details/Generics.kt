package j_details

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
