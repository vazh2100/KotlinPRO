package i_cancellation

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.coroutineContext

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    val job1 = scope.launch {
        timer1()
    }
    val job2 = scope.launch {
        timer2()
    }
    Thread.sleep(3000)
    job1.cancel()
    job2.cancel()
}

suspend fun timer1() {
    var totalSeconds = 0
    while (true) {
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        try {
            println("hey")
            if (coroutineContext.isActive) { // ensureActive
                println(String.format("Time: %02d:%02d", minutes, seconds))
                Thread.sleep(1000)
            } else {
                throw CancellationException()
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
        }

        totalSeconds++
    }
}

suspend fun timer2() {
    var totalSeconds = 0
    while (true) {
        coroutineContext.ensureActive()
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        println("hey")
        println(String.format("Time: %02d:%02d", minutes, seconds))
        Thread.sleep(1000)
        totalSeconds++
    }
}
// cancel делает из active корутины inactive.
// Это сделано для того, чтобы в процессе сложных операций данные не повредились.
// Ответственность за завершение корутины лежит на авторе suspend функции.
// Чтобы завершить корутину надо бросить CancellationException
// Правило: если suspend функция обёрнута в try catch то нужно пробрасывать дальше CancellationException, чтобы механизм
// отмены корутин работал
// Правило: Если мы создаём свою suspend функцию, то она должна поддерживать механизм отмены корутин
// У функции delay есть такая поддержка, поэтому конструкцию if (coroutineContext.isActive) можно убрать


