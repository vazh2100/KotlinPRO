package d_executors

import java.util.concurrent.Executors

fun main() {
    val processors = Runtime
        .getRuntime()
        .availableProcessors()
    println(processors)
    val executorService = Executors.newFixedThreadPool(processors)
    repeat(1_000_000) {
        executorService.execute {
            processImage(Image(it))
        }
    }
}

private fun processImage(image: Image) {
    println("Image $image is processing")
    Thread.sleep(1000)
    println("Image $image processed")
}

data class Image(val id: Int)

// Создание потока - это дорогое удовольствие, поэтому используют ограничения в отношении количества потоков.
// Executor Service - это очередь + стратегия в отношении потоков (фиксированное количество, динамическое и т.д.)
// FixedThreadPool, SingleThreadExecutor, CachedThreadPool
// Выполняемые задачи организуются в очередь и отдаются потокам.
// Executor  Service также решает вопрос управления жизненным циклом потоков.
// В Android в UI используется SingleThreadExecutor
// Для того, чтобы ресурс ядра процессора не тратился на переключение между потоками, лучше для трудоёмких задач использовать
// количество потоков равное количеству ядер.
// Для задач с сетью, чтение и запись в файл лучше подходит CachedThreadPool
