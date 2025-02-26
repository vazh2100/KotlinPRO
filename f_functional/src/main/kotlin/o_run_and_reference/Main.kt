package o_run_and_reference

import b_kotlin_serialization_2.PersonDataManager

fun main() {
    val a = PersonDataManager.profiles
        .run {  // для конфигурирования объекта и возвращения преобразованного объекта
            this.toString()
        }
        .apply {
            // для конфигурирования объекта, без преобразования типа
        }
        .let {
            // для преобразования типа и проверки на null
            it.toString()
        }
        .also {
            // для выполнения побочных действий связанных с объектом или логирование
            println(it)
        }
        .forEach(::println) // method reference
    val b = with(a) { // для конфигурирования и преобразования типа
        this.toString()
    }
}