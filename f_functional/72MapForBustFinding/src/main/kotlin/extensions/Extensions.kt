package com.vazh2100.extensions


object Extensions {
    inline fun <T> Collection<T>.forEach(operation: (T) -> Unit) {
        for (element in this) operation(element)
    }

    //Если не указать ключевое слово inline, то будет создаваться объект, который хранит в себе функцию operation, по сути
//анонимный объект, реализующий интерфейс содержащий функцию operation.
//А если указать ключевое слово inline, то в процессе компиляции вместо вызова функции operation вставится код функции, которую
// передали в качестве параметра.
    inline fun <K, T> Collection<K>.transform(operation: (K) -> T): List<T> {
        return this.map(operation)
    }


    //функция расширение
    inline fun <T> Iterable<T>.filter(
        filter: (T) -> Boolean
    ): List<T> {
        val filtered = mutableListOf<T>()
        for (item in this) {
            if (filter(item)) filtered.add(item)
        }
        return filtered
    }

    inline fun <T, R> T.letMy(operation: (T) -> R): R {
        return operation(this)
    }


    fun <T> T.also(block: (T) -> Unit): T {
        block(this)
        return this
    }
}