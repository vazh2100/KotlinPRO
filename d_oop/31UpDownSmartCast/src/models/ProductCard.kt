package models

class ProductCard(val name: String, val brand: String, val size: Int, val price: Float) {

    fun printInfo() {
        println(this)
    }

    override fun toString(): String {
        return "name='${this.name}', brand='$brand', size=$size, price=$price"
    }


}