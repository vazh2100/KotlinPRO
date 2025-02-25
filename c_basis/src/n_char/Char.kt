package n_char

fun main() {
//    val eng = 'a'..'z'
//    for (char in eng) {
//        println(char)
//    }
//
//    var ch = 'a'
//    while (ch <= 'z') {
//        println(ch)
//        ch++
//    }
//
//    var letter = '1'
//    letter.isLetter()

    val password = readln()

    var containLetter = false
    var containDigit = false
    var containNotLetterNotDigit = false
    val contain8letter = password.length >= 8
    if (!contain8letter) return println("Пароль меньше чем 8 цифр")
    for (letter in password) {
        if (!containLetter) {
            if (letter.isLetter()) {
                containLetter = true
                continue
            }
        } else if (!containDigit) {
            if (letter.isDigit()) {
                containDigit = true
                continue
            }
        } else if (!containNotLetterNotDigit) {
            if (!letter.isLetterOrDigit()) {
                containNotLetterNotDigit = true
                continue
            }
        } else {
            break
        }
    }
    if (!containLetter) return println("В пароле нет букв")
    if (!containDigit) return println("В пароле нет цифр")
    if (!containNotLetterNotDigit) return println("В пароле нет спецсимволов")
    return println("С паролем всё в порядке")

}