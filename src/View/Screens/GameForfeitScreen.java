package View.Screens;

import View.Buttons.ExitButton;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class GameForfeitScreen extends JFrame {
    public GameForfeitScreen(boolean isWhite) {
        super("Forfeited");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(new Dimension(200, 100));
        setResizable(false);
        setLocationRelativeTo(null);

        JTextPane textArea = new JTextPane();
        if(isWhite) textArea.setText("White Forfeit. Black Won.");
        else textArea.setText("Black Forfeit. White Won.");
        textArea.setEditable(false);
        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        add(textArea);

        ExitButton exitButton = new ExitButton();
        add(exitButton);
    }
}
