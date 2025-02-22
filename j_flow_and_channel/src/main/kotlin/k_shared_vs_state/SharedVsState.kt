package k_shared_vs_state

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.Executors

// scope
private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher + SupervisorJob())
private val state = MutableStateFlow<Int>(0)
private val shared = MutableSharedFlow<Int>()

fun main() {
    shared.onEach {
        delay(100)
        println("Shared flow 1: $it")
    }.launchIn(scope)

    shared.onEach {
        delay(10000)
        println("Shared flow 2: $it")
    }.launchIn(scope)
    state.onEach {
        delay(1000)
        println("State flow: $it")
    }.launchIn(scope)
    emitFromCoroutines()
//    emitFromNotCoroutine()
}

fun emitFromCoroutines() {
    Thread.sleep(11000)
    scope.launch {
        repeat(101) {
            shared.emit(it)
        }
    }

    scope.launch {
        repeat(101) {
            state.emit(it)
        }
    }
}

fun emitFromNotCoroutine() {
    Thread.sleep(1000)
    repeat(101) {
        shared.tryEmit(it)
        state.tryEmit(it)
    }

}
