package View.Buttons;

import View.Screens.SaveGameScreen;

import javax.swing.*;
import java.awt.*;

public class SaveGameButton extends JButton {
    Color backgroundColor = Color.GREEN;
    SaveGameScreen saveGameScreen = new SaveGameScreen();
    public SaveGameButton() {
        super("Save Game");
        setBackground(backgroundColor);
        addActionListener(e -> {
            saveGameScreen.setVisible(true);
        });
    }
}
