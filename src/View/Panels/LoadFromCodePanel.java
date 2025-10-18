package View.Panels;

import View.Buttons.LoadFromCodeButton;
import View.Listeners.ActionListenerStringInput;

import javax.swing.*;
import java.awt.*;

public class LoadFromCodePanel extends JPanel {
    public LoadFromCodePanel(ActionListenerStringInput loadFromCodeActionListener) {
        setLayout(new BorderLayout());
        JTextField codeReader = new JTextField(64);
        LoadFromCodeButton loadGameCodeButton = new LoadFromCodeButton(loadFromCodeActionListener, codeReader.getText());
        add(codeReader, BorderLayout.NORTH);
        add(loadGameCodeButton, BorderLayout.CENTER);
    }
}
