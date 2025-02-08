package f_job_hierarchy

import kotlinx.coroutines.*
import java.util.concurrent.Executors

private val dispatcher = Executors.newFixedThreadPool(4).asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    val job = scope.launch {
        launch {
            printNum(1)
        }

        launch {
            printNum(2)
        }
    }
    val job2 = scope.launch {
        launch {
            printNum(1)
        }

        launch {
            printNum(2)
        }
    }
    println(job.parent)
    println(job2.parent)
    Thread.sleep(3000)
    job.cancel()
    scope.cancel()
    dispatcher.close()
}

private suspend fun printNum(n: Int) {
    while (true) {
        println(n)
        delay(1000)
    }
}
// Есть основные потоки и потоки-daemon.
// Все  стандартные Dispatchers используют потоки демоны. Но можно сделать свой Dispatcher с основным потоком
// runBlocking {  } блокирует поток пока код внутри функции не будет выполнен, используют в основном в тестах
// Structured Concurrency:
// 2. Все корутины имеют иерархию. При отмене родительской Job, отменяются и дочерние.
// Родительская корутина не завершает работу, пока все её дочерние корутины не завершат работу.
// job.cancel и  scope.cancel() не завершает программу в целом в данном случаем.
// Лишь закрытие главных потоков завершит программу
// CoroutineConxtext - это  Name, Dispatcher и Job. Job - это родитель для всех корутин scope