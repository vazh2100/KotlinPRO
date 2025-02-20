package g_share_in

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.Executors

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher + SupervisorJob())

fun main() {
    scope.launch {
        timerShared.take(5).collect {
            println(it)
        }
    }
    scope.launch {
        delay(5000)
        timerShared.collect {
            println(it)
        }
    }
}

private val timerFlow = generateSequence(0) { it + 1 }.asFlow().onEach { delay(1000) }
private val timerShared = timerFlow.shareIn(scope, SharingStarted.WhileSubscribed())
//private val timerShared = MutableSharedFlow<Int>().apply {
//    scope.launch {
//        timerFlow.collect {
//            emit(it)
//        }
//    }
//}.asSharedFlow()

