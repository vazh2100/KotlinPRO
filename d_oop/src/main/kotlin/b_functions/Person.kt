package b_functions

class Person {

    var name: String = ""
    var age: Int = 0
    var height: Int = 0
    var weight: Int = 0

    fun sayHello() {
        println("Hello, my name is $name")
    }

    fun run() {
        println("I am $name and I run!")
    }
}