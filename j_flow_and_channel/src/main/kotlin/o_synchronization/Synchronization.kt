package o_synchronization

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.Executors

class Counter {

    private val mutex = Mutex()
    var value = 0

    suspend fun inc() {
        mutex.withLock {
            delay(1)
            value++
        }
//        mutex.lock()
//        try {
//            delay(1)
//            value++
//        } finally {
//            mutex.unlock()
//        }
    }
}

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    scope.launch {
        val counter = Counter()
        buildList {
            repeat(100) {
                scope.launch {
                    repeat(10) {
                        counter.inc()
                    }
                }.let { add(it) }
            }
        }.joinAll()
        println(counter.value)
    }

}