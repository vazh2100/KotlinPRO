package l_exceptions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import kotlin.random.Random

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    scope.launch {
        try {
            flowWithException.collect {
                println(it)
            }
        } catch (e: Exception) {
            println("Caught: $e")
        }
        flowWithException
            .map {
                try {
                    it / Random.nextInt(5)
                } catch (e: Throwable) {
                    10
                }
            }
            .catch { println("Caught: $it") }
            .collect {
                println(it)
            }

        flowWithException
            .map {
                it / Random.nextInt(5)
            }
            .retry(10) {
                false
            }
            .catch { println("Caught: $it") }
            .collect {
                println(it)
            }
    }
}

val flowWithException = flow {
    repeat(5) {
        if (it == 3) error("Exception")
        emit(it)
    }
}