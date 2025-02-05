package b_coroutines

import entities.Author
import entities.Book
import kotlinx.coroutines.*
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*
import kotlin.concurrent.thread

// По сути механизм coroutines - это callbacks
object Display {

    private val scope = CoroutineScope(CoroutineName("MyName"))

    private val infoArea = JTextArea().apply {
        isEditable = false
    }

    private val loadButton = JButton("Load book info").apply {
        addActionListener {
            // Создаём Coroutine. "launch" - это один из строителей корутин.
            scope.launch {
                isEnabled = false
                infoArea.text = "Loading book information...\n"
                val book = loadBook()
                println(book)
                infoArea.append(book.toString())
                infoArea.append("Loading author information...\n")
                val author = loadAuthor(book)
                println(author)
                infoArea.append(author.toString())
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
            }
        })
    }

    fun show() {
        mainFrame.isVisible = true
        startTimer()
    }

    private suspend fun loadBook(): Book {
        delay(3000)
        return Book("1984", 1949, "Dystopia")
    }

    private suspend fun loadAuthor(book: Book): Author {
        delay(3000)
        return Author("George Orwell", "British writer and journalist")
    }

    private fun startTimer() {
        thread {
            var totalSeconds = 0
            while (true) {
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60
                timerLabel.text = "Time: $minutes:$seconds"
                timerLabel.text = String.format("Time: %02d:%02d", minutes, seconds)
                Thread.sleep(1000)
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