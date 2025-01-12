package entities.collection

import entities.list.MyList
import entities.list.UnmodifiableList


//* преобразует массив в аргументы переменной длины
fun <T> myListOf(vararg elements: T): MyList<T> = UnmodifiableList(*elements)

