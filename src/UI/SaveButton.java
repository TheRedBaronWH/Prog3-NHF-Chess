package UI;

import Board.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveButton extends JButton {
    public SaveButton(String text) {
        super(text);

        addActionListener(e -> {
            JFrame window = new JFrame("Save Game");
            window.setSize(400, 150);
            window.setLocationRelativeTo(null);
            window.setLayout(new GridLayout(3, 1));
            Board[][] board = UI.getBoard();

            JTextPane textArea = new JTextPane();
            textArea.setText(Board.saveBoardCode(board));
            textArea.setEditable(false);
            StyledDocument doc = textArea.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            window.add(textArea);

            JPanel savePanel = new JPanel();
            JButton saveToFileButton = new JButton("Save to textFile");
            saveToFileButton.addActionListener(e1 -> {
                File fout = new File("BoardCode.txt");
                try{
                    if(!fout.exists()) fout.createNewFile();
                    FileWriter fw = new FileWriter(fout);
                    fw.write(textArea.getText());
                    fw.close();
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            savePanel.add(saveToFileButton);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            JButton goBackButton = new JButton("Go Back");
            goBackButton.addActionListener(e1 -> window.dispose());
            buttonPanel.add(goBackButton);

            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e1 -> System.exit(0));
            buttonPanel.add(exitButton);

            window.add(savePanel);
            window.add(buttonPanel);
            window.setVisible(true);
        });
    }
}
