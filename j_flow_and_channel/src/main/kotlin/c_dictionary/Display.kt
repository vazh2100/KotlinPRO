package c_dictionary

import kotlinx.coroutines.*
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
    private var loadingJob: Job? = null

    // state
    private lateinit var queries: Flow<String>

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

    init {
        queries.onEach {
            searchButton.isEnabled = false
            resultArea.text = "Loading..."
        }.map {
            repository.loadDefinition(it)
        }.catch {
            resultArea.text = "Not Found"
        }.onEach {
            resultArea.text = it.ifEmpty { "Not Found" }
        }.launchIn(scope)
    }

    //
    fun show() {
        mainFrame.isVisible = true
    }

    private fun onButtonPressed() = useCase()

    private fun onTextChanged(): Job = useCase()

    private fun useCase() = scope.launch {
        searchButton.isEnabled = false
        resultArea.text = "Loading..."
        delay(500)
        runCatching {
            repository.loadDefinition(textField.text.trim())
        }.onSuccess {
            resultArea.text = it.ifEmpty { "Not Found" }
        }.onFailure {
            ensureActive()
            resultArea.text = "Not Found"
        }
        searchButton.isEnabled = true
    }.also {
        loadingJob?.cancel()
        loadingJob = it
    }

}

fun main() {
    Display.show()
}