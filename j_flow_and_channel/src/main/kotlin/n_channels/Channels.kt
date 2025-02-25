package n_channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)
private val channel = Channel<Int>(5, BufferOverflow.DROP_OLDEST) {
    println("Undelivered: $it")
}

fun main() {
    scope.launch {
        repeat(100) {
            println("1 is sending")
            channel.send(it * 1)
            println("1 is sent")
            delay(10)
        }
    }
    scope.launch {
        repeat(100) {
            println("2 is sending")
            channel.send(it * 2)
            println("2 is sent")
            delay(20)
        }
        channel.close()
    }

    scope.launch {
        channel.consumeEach {
            println("Consumer 1: $it")
            delay(1000)
        }
        println("Closed")
    }

    scope.launch {
        channel.consumeEach {
            println("Consumer 2: $it")
            delay(1000)
        }
        println("Closed")
    }
}
