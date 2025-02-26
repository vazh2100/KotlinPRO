package a_get_set_1

class Person(
    private val name: String,
    private val height: Int, private val weight: Int
) {

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

    fun sayHello() {
        println("Hello, my name is $name")
    }

    override fun toString(): String {
        return "name=$name, age=$age, height=$height, weight=$weight"
    }
}