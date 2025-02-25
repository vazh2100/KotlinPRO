package l_super

class Appliances(name: String, brand: String, price: Float, val power: Short) : ProductCard(name, brand, price) {

    override fun toString(): String {
        return super.toString() + " power=$power"
    }
}