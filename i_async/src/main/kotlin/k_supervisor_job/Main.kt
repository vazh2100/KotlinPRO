package k_supervisor_job

import kotlinx.coroutines.*
import java.util.concurrent.Executors

private val parentJob = SupervisorJob()
private val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
    println("Exception caught")
}
private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher + exceptionHandler + parentJob)

fun main() {
    scope.launch {
        longOperation(3000, 1)
        error("")
    }
    scope.launch {
        longOperation(5000, 2)
    }

}

private suspend fun longOperation(timeMillis: Long, number: Int) {
    println("Started $number")
    delay(timeMillis)
    println("Finished $number")
}
// Если мы используем Job - все корутины отменяются при ошибке в одной из них, а если используем SuperVisorJob, то нет