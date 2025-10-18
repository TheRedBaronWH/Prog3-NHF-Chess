package View.Buttons;

import View.Listeners.ActionListener;
import javax.swing.*;
import java.awt.*;

public class RulesButton extends JButton {
    Color backgroundColor = Color.YELLOW;
    public RulesButton(ActionListener actionListener) {
        super("Rules");
        setBackground(backgroundColor);
        addActionListener(e -> {
            actionListener.execute();
        });
    }
}
