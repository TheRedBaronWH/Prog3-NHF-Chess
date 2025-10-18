package View.Buttons;

import View.Listeners.ActionListener;
import javax.swing.*;
import java.awt.*;

public class LoadFromFileButton extends JButton {
    Color backgroundColor = Color.GREEN;
    public LoadFromFileButton(ActionListener actionListener) {
        super("Load Game from file");
        setBackground(backgroundColor);
        addActionListener(e -> {
            actionListener.execute();
        });
    }
}
