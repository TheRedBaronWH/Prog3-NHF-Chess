package UI;

import Board.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {
    public static ActionListener ButtonClickedListener;
    boolean hasPiece = false;
    int i;
    int j;

    public void setHasPiece(boolean hasPiece) { this.hasPiece = hasPiece; }

    public boolean getHasPiece() { return hasPiece; }

    class ButtonClickedListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(hasPiece){
                Board[][] board = UI.getBoard();
                UI.UpdateUI(board[i][j].getPiece().availableMoves(board));
            }
        }
    }

    public Button(int i, int j) {
        hasPiece = false;
        this.i = i;
        this.j = j;
        addActionListener(new ButtonClickedListener());
    }
}
