import models.Person


fun main() {
//    print("Input name:")
//    val name = readln()
//    print("Input brand:")
//    val brand = readln()
//    print("Input size:")
//    val size = readln().toInt()
//    print("Input price:")
//    val price = readln().toFloat()
//    val productCard = ProductCard()
//    productCard.init(name = name, brand = brand, size, price)
//    productCard.printInfo()


    print("Input 1st name:")
    val name = readln()
    print("Input 1st age:")
    val age = readln().toInt()
    print("Input 1st height:")
    val height = readln().toInt()
    print("Input 1st weight:")
    val weight = readln().toInt()
    val first = Person()
    first.init(name, age, height, weight)
    first.printInfo()
    first.sayHello()
    first.run()
}