package View.Buttons;

import View.Listeners.ActionListenerStringInput;
import javax.swing.*;
import java.awt.*;

public class LoadFromCodeButton extends JButton {
    Color backgroundColor = Color.GREEN;
    public LoadFromCodeButton(ActionListenerStringInput actionListener, String code) {
        super("Load Game from code");
        setBackground(backgroundColor);
        addActionListener(e -> {
            actionListener.execute(code);
        });
    }
}
