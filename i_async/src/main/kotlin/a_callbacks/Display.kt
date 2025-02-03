package a_callbacks

import entities.Author
import entities.Book
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*

object Display {

    private val infoArea = JTextArea().apply {
        isEditable = false
    }

    private val loadButton = JButton("Load entities.Book").apply {
        addActionListener {
            isEnabled = false
            infoArea.text = "Loading book information...\n"
            val book = loadBook()
            infoArea.append(book.toString())
            infoArea.append("Loading author information...\n")
            val author = loadAuthor(book)
            infoArea.append(author.toString())
            isEnabled = true
        }
    }


    private val timerLabel = JLabel("Time: 00:00")

    private val topPanel = JPanel(BorderLayout()).apply {
        add(timerLabel, BorderLayout.WEST)
        add(loadButton, BorderLayout.EAST)
    }

    private val mainFrame = JFrame("entities.Book and Author info").apply {
        layout = BorderLayout()
        add(topPanel, BorderLayout.NORTH)
        add(JScrollPane(infoArea), BorderLayout.CENTER)
        size = Dimension(400, 300)
    }

    fun show() {
        mainFrame.isVisible = true
        startTimer()
    }

    private fun loadBook(): Book {
        Thread.sleep(3000)
        return Book("1984", 1949, "Dystopia")
    }

    private fun loadAuthor(book: Book): Author {
        Thread.sleep(3000)
        return Author("George Orwell", "British writer and journalist")
    }

    private fun startTimer() {
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

fun main() {
    Display.show()
}