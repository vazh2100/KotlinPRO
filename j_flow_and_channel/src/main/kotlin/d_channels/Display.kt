package d_channels

import c_dictionary.Repository
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import java.awt.BorderLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.util.concurrent.Executors
import javax.swing.*

object Display {

    //
    private val repository = Repository

    // scope
    private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    private val scope = CoroutineScope(dispatcher + SupervisorJob())

    // state
    private val queries: Channel<String> = Channel()

    // ui
    private val textFieldLabel = JLabel("Enter word")
    private val textField = JTextField(20).apply {
        addKeyListener(object : KeyAdapter() {
            override fun keyReleased(e: KeyEvent?) {
                onTextChanged()
            }
        })
    }
    private val searchButton: JButton = JButton("Search").apply {
        this.addActionListener { }
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

    init {
        @Suppress("OPT_IN_USAGE")
        queries.consumeAsFlow().onEach {
            println(it)
            searchButton.isEnabled = false
            resultArea.text = "Loading..."
        }.debounce(
            500
        ).map {
            try {
                repository.loadDefinition(it)
            } catch (_: Throwable) {
                ""
            }
        }.onEach {
            resultArea.text = it.ifEmpty { "Not Found" }
            searchButton.isEnabled = true
        }.launchIn(scope)
    }

    //
    fun show() {
        mainFrame.isVisible = true
    }

    private fun onTextChanged(): Job = scope.launch {
        queries.send(textField.text.trim())
    }

}

fun main() {
    Display.show()
}