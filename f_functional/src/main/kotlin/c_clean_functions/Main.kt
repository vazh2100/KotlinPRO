package c_clean_functions

fun main() {
}

//1. При одних и тех же параметрах результат должен быть один и тот же
//2. Чистые функции не должны содержать side effects
fun sum(
    a: Int,
    b: Int
): Int = a + b

val list = mutableListOf(0)

fun sum2(
    a: Int,
    b: Int
): Int {
    val result = a + b
    // это side effect, побочный эффект, функция выходит за пределы своей ответственности, это нарушает чистоту
    list.add(result)
    return result
}