package j_details

fun main() {
    val user = User(UserId(10), "Andrey")
    println(user)
}

// Int заменён на UserId с целью безопасности. Чтобы не передать id одного класса в id другого
data class User(val id: UserId, val name: String)

// Такая запись позволяет вставлять значение на место использования, то есть это inline class
// функции внутри такого класса при компиляции становятся статическими, то есть не принадлежат объекту
@JvmInline
value class UserId(val value: Int) {

    fun function() {
        println(value)
    }
}
