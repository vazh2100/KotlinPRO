package a_get_set_1

fun main() {
    val person = Person("Vasiliy", 180, 70)
    person.age = 10
    println(person.age)
    person.age = 9
    println(person.age)
}