package UI;

import Board.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {
    boolean hasPiece;
    boolean canMoveTo;
    int i;
    int j;
    int x;
    int y;

    public void setHasPiece(boolean hasPiece) { this.hasPiece = hasPiece; }
    public void setCanMoveTo(boolean canMoveTo, int x, int y) {
        this.canMoveTo = canMoveTo;
        this.x = x;
        this.y = y;
    }
    public void setPoz(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public boolean getHasPiece() { return hasPiece; }
    public boolean getCanMoveTo() { return canMoveTo; }

    class ButtonClickedListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Board[][] board = UI.getBoard();
            if(canMoveTo) {
                System.out.println(x + " " + y);
                Board.movePiece(board, board[x][y].getPiece() , i, j);
                UI.UpdateUI(board);
            }
            else {
                if (hasPiece) {
                    UI.UpdateUI(board[i][j].getPiece().availableMoves(board), i, j);
                }
            }
        }
    }

    public Button(int i, int j) {
        hasPiece = false;
        canMoveTo = false;
        this.i = i;
        this.j = j;
        addActionListener(new ButtonClickedListener());
    }
}
