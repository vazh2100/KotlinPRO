package ui

import entities.User
import repositories.UserRepository
import java.awt.Dimension
import java.awt.Font
import java.awt.Insets
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

class Display {

    private val textArea: JTextArea = JTextArea().apply {
        isEditable = false
        font = Font(Font.SERIF, Font.BOLD, 23)
        margin = Insets(16, 16, 16, 16)
    }

    fun show() {
        val scrollPane = JScrollPane(textArea)
        JFrame().apply {
            isVisible = true
            size = Dimension(1900, 500)
            isResizable = true
            add(scrollPane)
        }
        UserRepository
            .instance()
            .addObserver(this)
    }

    fun onChanged(users: List<User>) {
        textArea.text = users.joinToString("\n")
    }
}