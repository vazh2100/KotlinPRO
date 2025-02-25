package f_constructor_overload

class Rectangle {

    val height: Int
    private val width: Int

    //    constructor() {
//        this.height = 1
//        this.width = 1
//    }
//    constructor() : this(0, 0)
    constructor() : this(0)

    constructor(height: Int, width: Int) {
        this.height = height
        this.width = width
    }

    //    constructor(size: Int) {
//        this.height = size
//        this.width = size
//    }
    constructor(size: Int) : this(height = size, width = size)

    fun draw() {
        val widthString: String = "*  ".repeat(width)
        repeat(height) {
            println(widthString)
        }
    }
}