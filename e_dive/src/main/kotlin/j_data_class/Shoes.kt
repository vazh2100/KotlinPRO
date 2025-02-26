package j_data_class

import a_get_set_1.ProductType

data class Shoes(
    override val name: String,
    override val brand: String,
    override val price: Float,
    val size: Byte
) : ProductCard(
    name, brand, price, ProductType.SHOES
) {

    override fun toJson(): String {
        return super.toJson() + ";size=$size;productType=$productType"
    }

    companion object {
        fun fromJson(map: Map<String, String>): Shoes {
            val name = map["name"]!!
            val brand = map["brand"]!!
            val price = map["price"]!!.toFloat()
            val size = map["size"]!!.toByte()
            return Shoes(name, brand, price, size)

        }
    }

}