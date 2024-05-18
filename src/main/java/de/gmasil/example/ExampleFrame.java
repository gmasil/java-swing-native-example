package de.gmasil.example;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExampleFrame extends JFrame {
    public ExampleFrame() {
        super("Example App");
        setSize(250, 100);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
