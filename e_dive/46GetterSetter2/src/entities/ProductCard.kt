package entities



abstract class ProductCard {
    val name: String
    val brand: String
    val price: Float
    val productType: ProductType


    constructor(
        name: String, brand: String, price: Float, productType: ProductType
    ) {
        this.name = name
        this.brand = brand
        this.price = price
        this.productType = productType
    }

    constructor(map: Map<String, String>) {
        name = map["name"]!!
        brand = map["brand"]!!
        price = map["price"]!!.toFloat()
        productType = map["productType"]!!.let { ProductType.valueOf(it) }
    }

    open fun toJson(): String {
        return "name=$name;brand=$brand;price=$price"
    }


    abstract fun printInfo()

    override fun toString(): String = toJson()

    companion object {
        fun fromJson(json: String): ProductCard {
            val map = json.split(";").map { it.split("=") }.associate { it[0] to it[1] }
            val type = map["productType"]?.let { ProductType.valueOf(it) }
            return when (type) {
                ProductType.FOOD -> Food(map)
                ProductType.SHOES -> Shoes(map)
                ProductType.APPLIANCE -> Appliances(map)
                null -> throw Exception()
            }
        }
    }


}