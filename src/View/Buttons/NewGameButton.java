package View.Buttons;

import View.Listeners.ActionListener;
import javax.swing.*;
import java.awt.*;

public class NewGameButton extends JButton {
    Color backgroundColor = Color.GREEN;
    public NewGameButton(ActionListener actionListener) {
        super("New Game");
        setBackground(backgroundColor);
        addActionListener(e -> {
            actionListener.execute();
        });
    }
}
