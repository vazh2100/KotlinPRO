package models

//public - доступен везде
//internal - доступен внутри пакета
//protected - доступен внутри класса и для наследников
//private - доступен только внутри класса

class Person(
    private val name: String,
    private val age: Int,
    private val height: Int,
    private val weight: Int
) {

    fun printInfo() {
        println(this)
    }


    fun sayHello() {
        println("Hello, my name is $name")
    }

    fun run() {
        println("I am $name and I run!")
    }

    override fun toString(): String {
        return "name=$name, age=$age, height=$height, weight=$weight"
    }
}