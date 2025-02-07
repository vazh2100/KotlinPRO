package e_concurrency

import entities.Book
import kotlinx.coroutines.*
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.util.*
import java.util.concurrent.Executors
import javax.swing.*

@Suppress("MagicNumber")
// По сути механизм coroutines - это callbacks
object Display {

    // Создание CoroutineDispatcher из ExecutorService
    private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    private val timerDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    private val scope = CoroutineScope(dispatcher)
    private val timerScope = CoroutineScope(timerDispatcher)
    private val infoArea = JTextArea().apply {
        isEditable = false
    }
    private val loadButton = JButton("Load book info").apply {
        addActionListener {
            // Создаём Coroutine. "launch" - это один из строителей корутин.
            isEnabled = false
            infoArea.append("Loading book information...\n")
            val jobs = mutableListOf<Deferred<Book>>()
            repeat(10) {
                val job = scope.async {
                    val book = loadBook()
                    infoArea.append(book.toString())
                    book
                }
                jobs.add(job)
            }
            scope.launch {
                println(jobs.awaitAll())
                isEnabled = true
            }
        }
    }
    private val timerLabel = JLabel("Time: 00:00")
    private val topPanel = JPanel(BorderLayout()).apply {
        add(timerLabel, BorderLayout.WEST)
        add(loadButton, BorderLayout.EAST)
    }
    private val mainFrame = JFrame("Book and Author info").apply {
        layout = BorderLayout()
        add(topPanel, BorderLayout.NORTH)
        add(JScrollPane(infoArea), BorderLayout.CENTER)
        size = Dimension(400, 300)
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(p0: WindowEvent?) {
                scope.cancel()
                timerScope.cancel()
            }
        })
    }

    fun show() {
        mainFrame.isVisible = true
        startTimer()
    }

    private suspend fun heavyOperation() = withContext(Dispatchers.Default) {
        val a = mutableListOf<Int>()
        repeat(300_000) {
            a.add(0, it)
        }
    }

    private suspend fun loadBook(): Book {
        heavyOperation()
        return Book("1984", 1949, "Dystopia")
    }

    private fun startTimer() {
        timerScope.launch {
            var totalSeconds = 0
            while (true) {
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60
                timerLabel.text = "Time: $minutes:$seconds"
                timerLabel.text = String.format(Locale.ENGLISH, "Time: %02d:%02d", minutes, seconds)
                delay(1000)
                totalSeconds++
            }
        }
    }
}

fun main() {
    Display.show()
}
// Structured Concurrency - Структурированный параллелизм
// 1) Корутины должны быть запущены внутри области с определенным жизненным циклом.
// Это означает, что если нам больше не нужна область(вернулись на предыдущий экран, например), вся корутины области
// должны быть отменены.
//
// Coroutine context - это составное понятие. В него входят Имя, Dispatcher.
// Dispatcher-Default использует пул потоков фиксированной длины равный количеству физических ядер процессора
// Dispatcher-Unconfined запускает корутины в том потоке на котором её создали, но после приостановки дальнейшее
// выполнение зависит от приостанавливающей функции
// Dispatcher-IO использует Cached Thread Pool. Используется не для сложных  операций, а для работы с сетью,
// чтением из файлов, запись в файл, чтение и запись в базу данных. Работы процессора нет, но ждать нужно.
// Dispatchers Main использует SingleThreadExecutor
// Если мы не ждём от корутины результат, то используется launch. А если нужен результат, то используется
// async. async возвращает Deferred - наследник Job.
