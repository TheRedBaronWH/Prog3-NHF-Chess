package View.Panels;

import Model.Pieces.Piece;
import View.Buttons.ForfeitButton;

import javax.swing.*;
import java.awt.*;

public class BlackPlayerPanel extends JPanel {
    private static ForfeitButton forfeitButton = new ForfeitButton("Forfeit", false);
    private static JTextArea textArea = new JTextArea();
    private static JPanel scorePanel = new JPanel();
    private static JTextArea scoreText = new JTextArea("Score:");
    private static JTextArea score = new JTextArea("0");
    public BlackPlayerPanel() {
        setLayout(new BorderLayout());

        score.setEditable(false);
        score.setPreferredSize(new Dimension(300, 20));
        scorePanel.setLayout(new BorderLayout());
        scorePanel.add(scoreText, BorderLayout.WEST);
        scorePanel.add(score, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.NORTH);

        textArea.setPreferredSize(new Dimension(375, 180));
        textArea.setEditable(false);
        add(textArea, BorderLayout.CENTER);

        add(forfeitButton, BorderLayout.SOUTH);
    }

    public void UpdatePieceCounter(Piece piece) {
        score.setText(String.valueOf(Integer.parseInt(score.getText())+piece.getValue()));
        textArea.setText(piece.toString()+ " +" + piece.getValue() + "\n"+textArea.getText());
    }
}
