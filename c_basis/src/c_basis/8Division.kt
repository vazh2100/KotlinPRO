package c_basis

fun main() {
    /*   val a = 10
       val b = 3
       val division = (a / b).toDouble()
       println(division)*/
    print("Введите количество секунд:")
    val seconds = readln().toInt()
    val hours = seconds / 3600
    val leftSecondsForMinutes = seconds % 3600
    val minutes = leftSecondsForMinutes / 60
    val leftSeconds = leftSecondsForMinutes % 60
    println("$hours:$minutes:$leftSeconds")
}