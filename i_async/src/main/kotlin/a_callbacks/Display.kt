package a_callbacks

import entities.Author
import entities.Book
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*
import kotlin.concurrent.thread

object Display {

    private val infoArea = JTextArea().apply {
        isEditable = false
    }

    private val loadButton = JButton("Load book info").apply {
        addActionListener {
            isEnabled = false
            infoArea.text = "Loading book information...\n"
            loadBook { book ->
                infoArea.append(book.toString())
                infoArea.append("Loading author information...\n")
                loadAuthor(book) { author ->
                    infoArea.append(author.toString())
                    isEnabled = true
                }
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
    }

    fun show() {
        mainFrame.isVisible = true
        startTimer()
    }

    private fun loadBook(onLoaded: (Book) -> Unit) {
        thread {
            Thread.sleep(3000)
            onLoaded(Book("1984", 1949, "Dystopia"))
        }

    }

    private fun loadAuthor(book: Book, onLoaded: (Author) -> Unit) {
        thread {
            Thread.sleep(3000)
            onLoaded(Author("George Orwell", "British writer and journalist"))
        }

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