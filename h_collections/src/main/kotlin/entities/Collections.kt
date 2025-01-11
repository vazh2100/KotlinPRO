package entities


//* преобразует массив в аргументы переменной длины
fun <T> myListOf(vararg elements: T): MyList<T> = UnmodifiableList(*elements)

