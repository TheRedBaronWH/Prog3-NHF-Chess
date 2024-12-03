package UI;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class ForfeitButton extends JButton {
    public ForfeitButton(String text, boolean isWhite) {
        super(text);

        addActionListener(e -> {
            JFrame endGame = new JFrame("Forfeited");
            endGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            endGame.setLayout(new FlowLayout());
            endGame.setSize(new Dimension(200, 100));
            endGame.setResizable(false);
            endGame.setLocationRelativeTo(null);

            JTextPane textArea = new JTextPane();
            if(isWhite) textArea.setText("White Forfeit. Black Won.");
            else textArea.setText("Black Forfeit. White Won.");
            textArea.setEditable(false);
            StyledDocument doc = textArea.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            endGame.add(textArea);

            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e1 -> System.exit(0));
            endGame.add(exitButton);

            endGame.setVisible(true);
        });
    }
}
