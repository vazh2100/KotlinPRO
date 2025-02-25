package l_super

class Food(name: String, brand: String, price: Float, val calories: Short) : ProductCard(name, brand, price) {

    override fun toString(): String {
        return super.toString() + " calories=$calories"
    }
}