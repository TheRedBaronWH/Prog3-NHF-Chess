package View.Buttons;

import javax.swing.*;
import java.awt.*;

public class ExitButton extends JButton {
    Color backgroundColor = Color.RED;
    public ExitButton() {
        super("Exit");
        setBackground(backgroundColor);
        addActionListener(e -> {
            System.exit(0);
        });
    }
}