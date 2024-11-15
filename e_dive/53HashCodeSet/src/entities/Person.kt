package entities

class Person(
    private val name: String,
    family: String,
    private val height: Int,
    private val weight: Int
) {

    var family: String = family
        private set

    val fullName: String
        get() {
            return "$name $family"
        }


    var age: Int = 0
        get() {
            println("Не вежливо спрашивать о возрасте")
            return 1
        }
        set(value) {
            if (value < field) {
                println("Можно только увеличивать возраст")
            } else {
                field = value
            }
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