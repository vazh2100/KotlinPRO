package e_cold_flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.Executors

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher + SupervisorJob())

fun main() {
    val flow = timer()
    scope.launch {
        flow.collect {
            println(it)
        }
    }
    scope.launch {
        delay(5000)
        flow.collect {
            println(it)
        }
    }
}

private fun timer() = generateSequence(0) { it + 1 }.asFlow().onEach { delay(1000) }

