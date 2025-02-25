package o_serialization_deserialization

import m_enum.ProductType

class Appliances : ProductCard {

    val power: Short

    constructor(name: String, brand: String, price: Float, power: Short) : super(
        name, brand, price, ProductType.APPLIANCE
    ) {
        this.power = power
    }

    constructor(map: Map<String, String>) : super(map) {
        power = map["power"]!!.toShort()
    }

    override fun toJson(): String {
        return super.toJson() + ";power=$power;productType=$productType"
    }
}