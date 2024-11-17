package entities

data class Food(
    override val name: String,
    override val brand: String,
    override val price: Float,
    private val calories: Short
) : ProductCard(name, brand, price, ProductType.FOOD) {

    override fun toJson(): String {
        return super.toJson() + ";calories=$calories;productType=$productType"
    }

    companion object {
        fun fromJson(map: Map<String, String>): Food {
            val name = map["name"]!!
            val brand = map["brand"]!!
            val price = map["price"]!!.toFloat()
            val calories = map["calories"]!!.toShort()
            return Food(name, brand, price, calories)

        }
    }
}