package View.Buttons;

import Model.BoardManager;
import Model.BoardTile;
import Model.Vector.Vector;
import View.Screens.GameScreen;

import javax.swing.*;

public class BoardTileButton extends JButton {
    boolean hasPiece;
    Vector poz;

    boolean freeToBeMovedTo;
    Vector movingHereFrom;

    public void setHasPiece(boolean hasPiece) {
        this.hasPiece = hasPiece;
    }
    public void setPoz(int i, int j) {
        poz = new Vector(i, j);
    }
    public void movingHereFrom(int x, int y) {
        freeToBeMovedTo = true;
        movingHereFrom = new Vector(x, y);
    }
    public void notMovingHere(){
        freeToBeMovedTo = false;
        movingHereFrom = new Vector(0, 0);
    }

    public boolean getHasPiece() {
        return hasPiece;
    }
    public Vector getPoz() {
        return poz;
    }

    public BoardTileButton(int i, int j) {
        hasPiece = false;
        freeToBeMovedTo = false;
        poz = new Vector(i, j);
        addActionListener(e1 -> {
            int k = poz.getX();
            int l = poz.getY();
            int x = movingHereFrom.getX();
            int y = movingHereFrom.getY();
            BoardTile[][] board = BoardManager.getBoard();
            if (freeToBeMovedTo) {
                //System.out.println(x + " " + y);
                BoardTile.movePiece(board[x][y].getPiece(), k, l);
                GameScreen.UpdateUI();
            }
            else {
                if (hasPiece) {
                    GameScreen.UpdateUI_HighlightPossibleMoves(board[k][l].getPiece().getPossibleMoves(), k, l);
                }
            }
        });
    }
}
