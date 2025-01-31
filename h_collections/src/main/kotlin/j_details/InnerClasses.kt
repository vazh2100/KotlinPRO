package j_details

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JTextArea

class Display {

    val textArea = JTextArea()

    val button = JButton().apply {

        // Анонимный класс - это Inner Class
        addActionListener(object : ActionListener {
            override fun actionPerformed(p0: ActionEvent?) {
                textArea.text = "Clicked"
            }
        })
    }

    // Inner class - позволяет видеть свойства наружного класса
    private inner class ClickListener : ActionListener {
        override fun actionPerformed(p0: ActionEvent?) {
            textArea.text = "Clicked"
        }
    }
}

