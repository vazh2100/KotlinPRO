package f_hot_flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.util.concurrent.Executors

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher + SupervisorJob())

fun main() {
    scope.launch {
        timer.collect {
            println("Coroutine 1: $it")
        }
    }

    scope.launch {
        delay(3000)
        timer.collect {
            println("Coroutine 2: $it")
        }
    }
}

private val timerBackProperty = MutableSharedFlow<Int>().also {
    scope.launch {
        for (sec in 0..100) {
            it.emit(sec)
            delay(1000)
        }
    }
}
val timer = timerBackProperty.asSharedFlow()
