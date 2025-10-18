package View.Buttons;

import View.Screens.GameForfeitScreen;

import javax.swing.*;
import java.awt.*;

public class ForfeitButton extends JButton {
    Color backgroundColor = Color.RED;
    public ForfeitButton(String text, boolean isWhite) {
        super(text);

        setBackground(backgroundColor);
        addActionListener(e -> {
            GameForfeitScreen endGame = new GameForfeitScreen(isWhite);
            endGame.setVisible(true);
        });
    }
}
