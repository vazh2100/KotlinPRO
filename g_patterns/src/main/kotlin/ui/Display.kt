package ui

import repositories.UserRepositoryWithDelegate
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
        //Зависимость от абстракций, а не от реализаций
        UserRepositoryWithDelegate
            .instance()
            .addOnUserChangeListener { newValue -> textArea.text = newValue.joinToString("\n") }
    }

}