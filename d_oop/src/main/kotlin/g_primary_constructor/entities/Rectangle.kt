package g_primary_constructor.entities

//Если объявлен первичный конструктор, то вторичные конструкторы обязаны на него сослаться.
// Проигнорировать его нельзя. Либо ссылаемся на него прямо, либо ссылаемся на конструктор,
// который ссылается на первичный конструктор
class Rectangle(height: Int = 0, width: Int = 0) {

    val height: Int = height
    private val width: Int = width

    constructor (
        size: Int = 5
    ) : this(height = size, width = size)

    fun draw() {
        val widthString: String = "*  ".repeat(width)
        repeat(height) {
            println(widthString)
        }
    }
}