package models

class Shoes(name: String, brand: String, price: Float, val size: Byte) : ProductCard(name, brand, price) {
    override fun toString(): String {
        return super.toString() + " size=$size"
    }
}