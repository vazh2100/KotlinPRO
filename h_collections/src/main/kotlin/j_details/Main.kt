package j_details

open class Worker(val name: String)

class Programmer(name: String) : Worker(name)

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

fun main() {
    val worker = Container<Worker>()
    val programmer = Container(Programmer("b"))
    val director = Container(Director("c"))
    copy(programmer, worker)
    copy(director, worker)
    println(worker.value)
    println(programmer.value)
    println(director.value)


}


// Контр вариантность - наличие совместимости у производных типов обратного направления(тип T и его родители).
// Чтобы была контр вариантность, нужно указать ключевое слово in в производном типе
fun <T> copy(src: Container<T>, dst: Container<in T>) {
    dst.value = src.value
}

// out - producer, отдаёт данные наружу
// in - consumer, может только принимать данные
// producer - extends, consumer - super
