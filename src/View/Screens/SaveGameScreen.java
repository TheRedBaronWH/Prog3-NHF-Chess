package View.Screens;

import Model.BoardManager;
import Model.BoardTile;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class SaveGameScreen extends JFrame{
    public SaveGameScreen() {
        super("Save Game");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));
        BoardTile[][] board = BoardManager.getBoard();

        JTextPane textArea = new JTextPane();
        textArea.setText(BoardManager.getBoardCode());
        textArea.setEditable(false);
        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        add(textArea);

        JPanel savePanel = new JPanel();
        JButton saveToFileButton = new JButton("Save to textFile");
        saveToFileButton.addActionListener(e1 -> BoardManager.saveBoard());
        savePanel.add(saveToFileButton);
        add(savePanel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(e1 -> dispose());
        buttonsPanel.add(backButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e1 -> System.exit(0));
        buttonsPanel.add(exitButton);
        add(buttonsPanel);
    }
}
