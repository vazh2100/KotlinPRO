import java.lang.Thread.sleep
import kotlin.concurrent.thread

fun main() {
    game()
    return
    Thread {
        repeat(100_000) {
            print(" 1 ")
            sleep(1000)
        }
    }.start()

    thread {
        repeat(100_000) {
            print(" 2 ")
            sleep(1000)
        }
    }

    repeat(100_000) {
        print(" 3 ")
        sleep(1000)
    }

}

fun game() {
    println("Enter number to guess: ")
    readln()
        .toInt()
        .also { num ->
            var guessed = false

            // Поток для бинарного поиска
            thread {
                val range = 0..Long.MAX_VALUE // Ленивый диапазон вместо списка
                var lowerBound = 0L
                var upperBound = range.last

                // бинарный поиск
                while (!guessed) {
                    val mid = (lowerBound + upperBound) / 2
                    when {
                        mid < num -> lowerBound = mid + 1
                        mid > num -> upperBound = mid - 1
                        else -> guessed = true
                    }
                    println("Guessing: $mid")
                }
                println("Guessed the number: $num")
            }

            thread {
                var sec = 0
                while (!guessed) {
                    println(sec++)
                    sleep(1000)
                }
            }

        }
}

// let нужно преобразование типа, не нужно конфигурировать объект
// apply не нужно преобразование типа, нужно конфигурировать объект
// also не нужно преобразование типа, не нужно конфигурировать объект
// run нужно преобразовывать тип, нужно конфигурировать объект
// with нужно преобразовать тип, нужно конфигурировать объект
// takeIf нужно проверить объект на условие