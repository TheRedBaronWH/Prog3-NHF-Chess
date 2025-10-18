package View.Dialogs;

import View.Buttons.ExitButton;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class GameEndDialog extends JDialog {
    public GameEndDialog(String winner) {
        super();

        setModal(true);
        setTitle("Game End");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2,1));

        JTextPane winnerText = new JTextPane();
        String text = winner + " is the winner!";
        StyledDocument doc = winnerText.getStyledDocument();
        Style style = doc.addStyle("CustomStyle", null);
        StyleConstants.setFontFamily(style, "Arial");
        StyleConstants.setFontSize(style, 24);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
        StyleConstants.setBold(style, true);
        try {
            doc.insertString(0, text, style);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        winnerText.setEditable(false);
        add(winnerText);

        ExitButton exitButton = new ExitButton();
        add(exitButton);
    }
}
