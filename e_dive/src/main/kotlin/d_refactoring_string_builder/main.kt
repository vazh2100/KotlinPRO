package d_refactoring_string_builder

//Этот код в тысячу раз медленнее и нагружает сборщик мусора, занимает память
//fun append() {
//    val strings = 1..1000000
//    var content = ""
//    for (string in strings) {
//        content += string
//    }
//    println(content)
//}
//Этот код в тысячу раз быстрее и не нагружает сборщик мусора, не занимает память
fun append2() {
    val strings = 1..1000000
    val builder = StringBuilder(1000001)
    builder.capacity()
    for (string in strings) {
        builder.append(string)
    }
    println(builder.toString())
}

fun main() {
    append2()
    val accountant = Accountant("Alice", 30, 30000)
    accountant.work()
}