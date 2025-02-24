package m_flow_on

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.Executors

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    scope.launch {
        timerFlow.onStart {
            println("onStart on $threadName")
        }.onEach {
            println("onEach1 $it on $threadName")
        }.flowOn(dispatcher).map {
            println("map $it: $threadName")
            it
        }.flowOn(Dispatchers.Default).onEach {
            println("onEach2 $it on $threadName")
        }.collect {
            println("collect $it on $threadName")
        }
    }

}

private val timerFlow = generateSequence(0) { it + 1 }.asFlow().onEach {
    println("emit $it in $threadName")
    delay(1000)
}
private val threadName: String
    get() = Thread.currentThread().name
