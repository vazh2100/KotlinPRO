package f_variable_change

fun main() {
    var temp = readln().toInt()
    var acON = false
    if (temp > 25) {
        acON = true
    } else if (temp < 20) {
        acON = false
    } else {
        println("The AC is idle")
    }

    if (acON) {
        temp -= 5
    } else  {
        temp += 5
    }
    println("The AC is $acON")
    println("The temp is $temp")
}