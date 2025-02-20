package h_backpressure

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Executors

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher + SupervisorJob())
fun main() {
    val flow = flow {
        repeat(100) {
            println("Emitted: $it")
            emit(it)
            println("After emit $it")
            delay(100)
        }
    }.buffer(capacity = 10, BufferOverflow.DROP_OLDEST)

    scope.launch {
        flow.collect {
            println("Collected: $it")
            delay(1000)
            println("Processed: $it")
        }
    }
}
