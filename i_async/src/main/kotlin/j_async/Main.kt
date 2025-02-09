package j_async

import kotlinx.coroutines.*
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val handler = CoroutineExceptionHandler { context, throwable ->
    println(throwable)
}
private val scope = CoroutineScope(dispatcher + handler)

fun main() {
    val deferred = scope.async {
        method()
    }

    scope.launch {
        try {
            deferred.await()
        } catch (e: Throwable) {
            println("")
        }
    }
    Thread.sleep(1000)
    scope.launch {
        method2()
    }
}

suspend fun method(): String {
    error("Error")
}

suspend fun method2() {
    println("Not Error")
}
// Если использовать async, то независимо от того, используем мы handler или нет ошибка в корень не дойдёт, но корутины все
// отменятся. Если использовать await, то тогда ошибка дойдёт до корня. Но scope всё равно будет испоганен.
// Если запустить async внутри launch, то в этом случае ошибка в корневую job дойдёт как ошибка, а не как Deffered

