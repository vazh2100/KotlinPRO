import entities.Item
import entities.map.MyHashMap
import java.util.*

/// Технический долг ( сделать перед переходом к следующему разделу)

/// Во всех mutable iterator реализовать remove
/// в MyHashMap реализовать три типа итерации с запретом на модификацию
/// Реализовать LinkedHashMap
/// Реализовать TreeSet с помощью красно-черного дерева
/// Реализовать TreeHashMap
/// get и containsKey сделать едиными


// переделать containsKey(key: K) используя метод получения
fun main() {
    val myMap = MyHashMap<Int, Int>()
    repeat(10) {
        val a = myMap.put(0, it * 16)
        println(a)
    }

}

fun theory() {
    //Создают linked hash set
    setOf("a", "b") // неизменяемый
    mutableSetOf("a", "b") // изменяемый
    linkedSetOf("a", "b")  // изменяемый

    //Создаёт HashSet. HashSet является изменяемой коллекцией
    hashSetOf("").remove("")

    //Создаёт TreeSet. Она является изменяемой
    TreeSet<Item>()
    sortedSetOf<Item>()

    //toList создаёт новую копию, но делает её mutable, поэтому добавление возможно после приведения типов
    (mutableListOf<Int>(1, 2, 3).toList() as MutableList).add(1) // ошибки нет


    //listOf создаёт действительно не изменяемую коллекцию
    (listOf<Int>(1, 2, 3) as MutableList).add(1) // ошибка

    //создаёт HashMap
    hashMapOf("" to "")

    //создаёт LinkedHashMap
    mutableMapOf("" to "")
    //создаёт TreeMap
    sortedMapOf("" to "")
    // 1) отличие set и list
    // set не хранит дубликатов и не имеет методов работы по индексу (get, insert, remove)
    // 2) Отличие ArrayList от Linked List
    // в основе AL лежит массив, а в основе LL связанные ссылающиеся друг на друга элементы
    // Вставка в начало AL O(n), LL O(1)
    // Вставка в начало AL O(n), LL O(1)
    // Вставка по индексу AL O(n) LL O(n/2)
    // Получение по индексу O(1) O(n)
    // 3) В каких случаях стоит использовать LL вместо AL?
    // Если нужно вставлять, получать элементы в начале и конце списка и не использовать метод contains
    // 4) Реализации Set и их отличия
    // HashSet, LinkedHashSet, TreeSet
    // HS хранит случайно, итерирует случайно, LHS хранит случайно, итерирует в порядке добавления. TreeSet -
    // хранит упорядочено, итерирует упорядоченно
    // TreeSet - все операции O(log(n)), HS, LHS - все операции O(1) до O(log(n))
    // 4) Как будет остортирован treeSet с data class User (val  id:Int, val age:Int)?
    // Никак, так как класс не наследуется от интерфейса Comparable и выбросится исключение
    // но если передадим компаратор, то ошибки не будет
    // 5) Как будет остортирован treeMap если добавить в качестве значений data class User (val  id:Int, val age:Int)?
    // порядок сортировки будет зависеть от ключей по которым они будут добавлены
    // 6) Зачем нужны методы equals и hashCode?
    // equals нужен, чтобы сравнивать на равенство двух объектов
    // hashCode, чтобы представить объект в виде числа
    // 7) Правила для методов equals и hashCode
    // определил equals - определи и hashcode
    // если хэш коды не равны, то объекты точно не равны
    // если объекты равны по equals, то и hashCode у них одинаковый
    // если хэшкоды равны, то это не значит, что объекты равны по equals
    // 8) Могул ли в коллекции мап хранится два одинаковых значения и ключа
    // ключи нет, значения - да
    // Почему алгоритмическая сложность HashSet в лучшем случае O(1)?
    // Потому что позиция в хэш таблице вычисляется по hashCode объекта за одну операцию
    // 9) Можно ли хранить в качестве ключей HashMap объекты с изменяемыми свойствами? Какие последствия?
    // Нельзя. Потому что мы не сможем найти, получить и удалить такой элемент из коллекции после изменения.
    // А также возможна ситуация, что в коллекции окажется 2 одинаковых ключа, если мы добавим элемент со старыми
    // значениями
    // 10) Какой интерфейс на вершине иерархии коллекций?
    // Iterable. Назначение - делать коллекции способными к перебору, предоставляет итератор
    // Для коллекций Map на вершине Map
    // 11) Почему Мап находится вне иерархии коллекции?
    // Потому что параметризируется двумя типами ключом и значением. Все остальные коллекции принимают только значения
    // Поэтому они не совместимы с остальными коллекциями
    // 12) Алгоритмическая сложность опеераций в ArrayList.
    // add O(1), get O(1), removeAt O(n), remove O(2n), contains O(n), insert O(n),
    // 13) Алгоритмическая сложность операций в LinkedList.
    // add O(1), insert O(1 до n/2), get O(n), removeAt O(1 до n/2), remove O(n), contains O(n)
    // 14) Алгоритмическая сложность операций в HashSet и LinkedHashSet.
    // add O(1 до log(n)), remove O(1 до log(n)), contains O(1 до log(n)),
    // 15) Алгоритмическая сложность операций в TreeSet.
    // add O(log(n)), remove O(log(n)), contains O(log(n)),
    // 16) Алгоритмическая сложность операций в HashMap и LinkedHashMap.
    // put O(1 до log(n)), get O(1 до log(n)), remove O(1 до log(n)), containsKey O(1 до log(n)), containsValue O(n)
    // 17) Алгоритмическая сложность операций в TreeMap.
    // put O(log(n)), get O(log(n)), remove O(log(n)), containsKey O(log(n)), containsValue O(n)
}
