package c_dictionary

import kotlinx.coroutines.*
import java.awt.BorderLayout
import java.util.concurrent.Executors
import javax.swing.*

object Display {

    //
    private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    private val scope = CoroutineScope(dispatcher + SupervisorJob())

    //
    private val repository = Repository

    //
    private val textFieldLabel = JLabel("Enter word")
    private val textField = JTextField(20)
    private val searchButton: JButton = JButton("Search").apply {
        this.addActionListener { onButtonPressed() }
    }
    private val resultArea = JTextArea(25, 40).apply {
        isEditable = false
        lineWrap = true
        wrapStyleWord = true
    }
    private val topPanel = JPanel().apply {
        add(textFieldLabel)
        add(textField)
        add(searchButton)
    }
    private val mainFrame = JFrame("Dictionary App").apply {
        layout = BorderLayout()
        add(topPanel, BorderLayout.NORTH)
        add(JScrollPane(resultArea), BorderLayout.CENTER)
        pack()
    }

    //
    fun show() {
        mainFrame.isVisible = true
    }

    private fun onButtonPressed() = scope.launch {
        searchButton.isEnabled = false
        resultArea.text = "Loading..."
        runCatching {
            repository.loadDefinition(textField.text.trim())
        }.onSuccess {
            resultArea.text = it.ifEmpty { "Not Found" }
        }.onFailure {
            ensureActive()
            resultArea.text = "Not Found"
        }
        searchButton.isEnabled = true
    }

}

fun main() {
    Display.show()
}