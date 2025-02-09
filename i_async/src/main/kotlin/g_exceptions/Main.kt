package g_exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val handler = CoroutineExceptionHandler { context, throwable ->
    println(throwable)
}
private val scope = CoroutineScope(dispatcher + handler)

fun main() {
    val d = scope.launch {
        method()
        try {
            method()
        } catch (e: Exception) {
            println("yes")
        }
        // handler игнорируется в дочерних корутинах
        launch(handler) {
            method()
        }
    }
    Thread.sleep(5000)
    scope.launch {
        println("Не выводится")
    }
    Thread.sleep(5000)
}

suspend fun method(): String {
    error("Error")
}
// если в дочерней корутине было брошено исключение, то оно обрушивает весь scope.
// Чтобы обработать исключение в корневой Job, нужно добавить CoroutineExceptionHandler.
// Но это не оживляет scope. корневой Job отменяет все дочерние Coroutine
// CoroutineExceptionHandler - это ещё одна составляющая корутин context
// Передача exceptionHandler в дочернюю корутину игнорируется
