package models

class ProductCard {
    val name: String
    val brand: String
    val size: Int
    val price: Float

    constructor(
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