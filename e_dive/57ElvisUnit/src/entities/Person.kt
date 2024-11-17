package entities

// Data class автоматически реализует toString, equals, hashCode и copy из полей, которые находятся
// в первичном конструкторе.
// Такой класс нельзя наследовать.
// Свойства, которые объявлены вне первичного конструктора не используются в автоматической реализации.
// Но можно в ручную переопределить все методы кроме copy.
// Чтобы наследоваться от другого класса в первичном конструкторе нужно использовать override, а в родителе
// сделать свойства open.
//Абстрактный или открытый класс нельзя сделать data class
data class Person(
    val name: String,
    val family: String,
    val height: Int,
    val weight: Int,
) {

    //Это свойство игнорируется
    val fullName: String
        get() {
            return "$name $family"
        }

    //Это свойство игнорируется в copy и других методах
    var age: Int = 0
        get() {
            println("Не вежливо спрашивать о возрасте")
            return field
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

    override fun toString(): String {
        return "Person(name='$name', family='$family', height=$height, weight=$weight, age=$age)"
    }

}