package d_this_named_params

class ProductCard {

    var name: String = ""
    var brand: String = ""
    var size: Int = 0
    var price: Float = 0.0f

    fun init(
        name: String, brand: String, size: Int, price: Float
    ) {
        this.name = name
        this.brand = brand
        this.size = size
        this.price = price
    }

    fun printInfo() {
        println(this)
    }

    override fun toString(): String {
        return "name='${this.name}', brand='$brand', size=$size, price=$price"
    }

}