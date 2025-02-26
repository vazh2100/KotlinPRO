package j_data_class

import a_get_set_1.ProductType

abstract class ProductCard(
    open val name: String,
    open val brand: String,
    open val price: Float,
    val productType: ProductType
) {


    open fun toJson(): String {
        return "name=$name;brand=$brand;price=$price"
    }

    override fun toString(): String = toJson()

    companion object {
        fun fromJson(json: String): ProductCard {
            val map = json.split(";").map { it.split("=") }.associate { it[0] to it[1] }
            val type = map["productType"]?.let { ProductType.valueOf(it) }
            return when (type) {
                ProductType.FOOD -> Food.fromJson(map)
                ProductType.SHOES -> Shoes.fromJson(map)
                ProductType.APPLIANCE -> Appliances.fromJson(map)
                null -> throw Exception()
            }
        }
    }


}