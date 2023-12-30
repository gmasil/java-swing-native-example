package de.gmasil.example

import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame

class ExampleFrame() : JFrame("Example App") {
    init {
        setSize(250, 100)
        isVisible = true
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(we: WindowEvent) {
                dispose()
            }
        })
    }
}
