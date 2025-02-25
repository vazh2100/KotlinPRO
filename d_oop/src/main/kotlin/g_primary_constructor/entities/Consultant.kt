package g_primary_constructor.entities

class Consultant(val name: String, private val age: Int? = null) {

    fun sayHello() {
        val ageMessage = age?.let { " I'm $it years old." } ?: ""
        println("Hi, my name is $name.$ageMessage")
    }
}