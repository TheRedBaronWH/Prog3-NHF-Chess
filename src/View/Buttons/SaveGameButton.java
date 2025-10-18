package View.Buttons;

import Model.BoardManager;
import Model.BoardTile;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class SaveGameButton extends JButton {
    Color backgroundColor = Color.GREEN;
    public SaveGameButton(String text) {
        super(text);
        setBackground(backgroundColor);
        addActionListener(e -> {
            JFrame window = new JFrame("Save Game");
            window.setSize(400, 150);
            window.setLocationRelativeTo(null);
            window.setLayout(new GridLayout(3, 1));
            BoardTile[][] board = BoardManager.getBoard();

            JTextPane textArea = new JTextPane();
            textArea.setText(BoardManager.getBoardCode());
            textArea.setEditable(false);
            StyledDocument doc = textArea.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            window.add(textArea);

            JPanel savePanel = new JPanel();
            JButton saveToFileButton = new JButton("Save to textFile");
            saveToFileButton.addActionListener(e1 -> BoardManager.saveBoard());
            savePanel.add(saveToFileButton);

            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());
            JButton goBackButton = new JButton("Go Back");
            goBackButton.addActionListener(e1 -> window.dispose());
            buttonsPanel.add(goBackButton);

            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e1 -> System.exit(0));
            buttonsPanel.add(exitButton);

            window.add(savePanel);
            window.add(buttonsPanel);
            window.setVisible(true);
        });
    }
}
