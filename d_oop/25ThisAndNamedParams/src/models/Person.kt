package models

class Person {
    var name: String = ""
    var age: Int = 0
    var height: Int = 0
    var weight: Int = 0

    fun init(name: String, age: Int, height: Int, weight: Int) {
        this.name = name
        this.age = age
        this.height = height
        this.weight = weight
    }

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