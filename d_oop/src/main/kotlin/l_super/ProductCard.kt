package l_super

open class ProductCard(val name: String, val brand: String, val price: Float) {

    fun printInfo() {
        println(this)
    }

    override fun toString(): String {
        return "name='${this.name}', brand='$brand', price=$price"
    }

}