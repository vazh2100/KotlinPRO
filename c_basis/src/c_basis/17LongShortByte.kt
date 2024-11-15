package c_basis

fun main() {
//    val numbers = 1L..1_000_000_000L
//    var sum = 0L
//    for (num in numbers){
//        sum +=num
//    }
//    println(sum)

//    var num:Byte = 127 // -128..127
//    num++
//    println(num)

//    var short: Short = -32768 //-32768..32767
//    short--
//    println(short)

//    var int: Int = 2147483647 // - 2147483648..2147483647
//    int++
//    println(int)

//    var long:Long = 9223372036854775807 // -9223372036854775807..9223372036854775807
//    long++
//    println(long)
    print("Сколько лет: ")
    val age = readln().toInt()
    val result = age* 365 * 24 * 60 *60L
    println(result)
    println(result / 60 /60 / 24 / 365)
}