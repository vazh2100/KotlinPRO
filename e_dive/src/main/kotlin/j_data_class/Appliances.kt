package j_data_class

import a_get_set_1.ProductType

class Appliances(
    override val name: String,
    override val brand: String,
    override val price: Float,
    private val power: Short
) : ProductCard(
    name, brand, price, ProductType.APPLIANCE
) {

    override fun toJson(): String {
        return super.toJson() + ";power=$power;productType=$productType"
    }

    companion object {
        fun fromJson(map: Map<String, String>): Appliances {
            val name = map["name"]!!
            val brand = map["brand"]!!
            val price = map["price"]!!.toFloat()
            val power = map["power"]!!.toShort()
            return Appliances(name, brand, price, power)

        }
    }
}