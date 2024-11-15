package c_basis

fun main(args: Array<String>) {

    dz();
    return
    print("Input your name: ")
    val name = readln()
    print("Input your age: ")
    val age = readln()
    print("Input your sex: ")
    val sex = readln()
    print("Input your height: ")
    val height = readln()
    print("Input your weight: ")
    val weight = readln()
    println("name: \"$name\" \nage: \"$age\" \nsex: \"$sex\" \nheight: \"$height\" \nweight: \"$weight\"")
}

fun dz() {
    print("Поставьте оценку от 1 до 5:")
    val rate = readln()
    print("Оставьте комментарий:")
    val comment = readln()
    println("Спасибо за вашу оценку!")
    println("Вы поставили: $rate")
    println("Вы оставили комментарий: $comment")

}