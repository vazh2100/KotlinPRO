package c_basis

fun main(args: Array<String>) {
    multiple()
    return
//    age()
//    return
//    println("How many apples does John have?")
//    val john: Int = readln().toInt()
//    val nick = john + 7
//    val sum = john + nick
//    println("John and Nick have $sum apples")
}

fun age() {
    println("Enter first age:")
    val firstAge : Int = readln().toInt()
    println("Enter second age:")
    val secondAge = readln().toInt()
    val diff = firstAge - secondAge
    println("Difference: $diff")
}

fun multiple() {
    println("Enter first number:")
    val firstNumber = readln().toInt()
    println("Enter second number:")
    val secondNumber = readln().toInt()
    val multiple = firstNumber * secondNumber
    println("Multiple: $multiple")
}