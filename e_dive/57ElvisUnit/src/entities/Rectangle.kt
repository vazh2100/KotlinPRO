package entities

class Rectangle(
    var height: Int,
    var width: Int,
    test: Int
) {

    //Второй случай, когда нужны геттеры и сеттеры
    //Когда свойство вычисляется относительно других полей
    //Если эти поля изменились, то при вычислении будут использоваться новые значения
    //если будет val area:Int = height * width, то изменения не применятся
    val area: Int
        get() = height * width

    // поле публичное, его можно получать везде, но изменять его можно только внутри класса
    var test: Int = test
        private set

    constructor (
        size: Int = 5
    ) : this(height = size, width = size, test = 0) {
        test = 9
    }


    fun draw() {
        val widthString: String = "*  ".repeat(width)
        repeat(height) {
            println(widthString)
        }
    }
}