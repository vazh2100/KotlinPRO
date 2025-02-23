package j_state_flow

import c_dictionary.Repository
import i_screen_state_as_flow.ScreenState
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
    private val state = MutableStateFlow<ScreenState>(ScreenState.Initial)

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
        @Suppress("OPT_IN_USAGE") queries.consumeAsFlow()
            .onEach {
                state.emit(ScreenState.Loading)
            }.debounce(
                500
            ).onEach {
                if (it.isEmpty()) {
                    state.emit(ScreenState.Initial)
                } else {
                    try {
                        val content = repository.loadDefinition(it)
                        if (content.isEmpty()) {
                            state.emit(ScreenState.NotFound)
                        } else {
                            state.emit(ScreenState.Loaded(content))
                        }
                    } catch (_: Throwable) {
                        state.emit(ScreenState.NotFound)
                    }
                }
            }.launchIn(scope)

        state.onEach {
            println(it)
            when (it) {
                ScreenState.Initial -> {
                    resultArea.text = ""
                    searchButton.isEnabled = false
                }
                ScreenState.Loading -> {
                    resultArea.text = "Loading..."
                    searchButton.isEnabled = false
                }
                ScreenState.NotFound -> {
                    resultArea.text = "Not Found"
                    searchButton.isEnabled = true
                }
                is ScreenState.Loaded -> {
                    resultArea.text = it.content
                    searchButton.isEnabled = true
                }
                ScreenState.Error -> {}
            }
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