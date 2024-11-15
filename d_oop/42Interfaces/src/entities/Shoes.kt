package entities

class Shoes : ProductCard {
    val size: Byte

    constructor(name: String, brand: String, price: Float, size: Byte) : super(name, brand, price, ProductType.SHOES) {
        this.size = size
    }


    constructor(map: Map<String, String>) : super(map) {
        size = map["size"]!!.toByte()
    }

    override fun toJson(): String {
        return super.toJson() + ";size=$size;productType=$productType"
    }

    override fun printInfo() = println(toJson())

}