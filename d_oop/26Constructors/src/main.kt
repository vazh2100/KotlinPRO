import models.ProductCard


fun main() {
    print("Input name:")
    val name = readln()
    print("Input brand:")
    val brand = readln()
    print("Input size:")
    val size = readln().toInt()
    print("Input price:")
    val price = readln().toFloat()
    val productCard = ProductCard(name = name, brand = brand, size, price)
    productCard.printInfo()

}