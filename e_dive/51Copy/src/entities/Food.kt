package entities

class Food : ProductCard {
    private val calories: Short

    constructor(name: String, brand: String, price: Float, calories: Short) : super(
        name, brand, price, ProductType.FOOD
    ) {
        this.calories = calories

    }

    constructor(map: Map<String, String>) : super(map) {
        calories = map["calories"]!!.toShort()
    }

    override fun toJson(): String {
        return super.toJson() + ";calories=$calories;productType=$productType"
    }

    override fun printInfo() {
        println(toJson())
    }
}