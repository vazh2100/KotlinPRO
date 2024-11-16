package entities

class Person(
    val name: String,
    val family: String,
    val height: Int,
    val weight: Int,
) {

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

    fun copy(
        name: String = this.name,
        family: String = this.family,
        height: Int = this.height,
        weight: Int = this.weight,
        age: Int = this.age
    ): Person {
        return Person(name, family, height, weight).also { it.age = age }
    }


    override fun toString(): String {
        return "Person(name='$name', family='$family', height=$height, weight=$weight, age=$age)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Person) return false

        if (name != other.name) return false
        if (family != other.family) return false
        if (height != other.height) return false
        if (weight != other.weight) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + family.hashCode()
        result = 31 * result + height
        result = 31 * result + weight
        result = 31 * result + age
        return result
    }


}