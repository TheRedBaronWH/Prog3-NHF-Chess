package Model.Pieces;

import Model.BoardManager;
import Model.BoardTile;
import Model.Vector.MoveVector;
import Model.Vector.Vector;

import javax.swing.*;

public class Bishop extends Piece {
    public Bishop(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
        value = 3;
        if(white) setIcon(new ImageIcon(iconSource + "WBishop.png"));
        else setIcon(new ImageIcon(iconSource + "BBishop.png"));
    }

    MoveVector[] availableMoves(){
        BoardTile[][] board = BoardManager.getBoard();
        if(isWhite() != BoardManager.getWhiteTurn()) return new MoveVector[0];
        int x = getPoz().getX();
        int y = getPoz().getY();
        MoveVector[] moves = new MoveVector[0];
        Boolean[] stop = new Boolean[4];
        for(int i = 0; i<4; i++){
            stop[i] = false;
        }
        for(int i = 1; i < 8; i++){
            if(x-i>=0 && y+i<=7 && !stop[0])
                moves = addMove(board, moves, x - i, y + i, stop, 0);
            if(x+i<=7 && y+i<=7 && !stop[1])
                moves = addMove(board, moves, x + i, y + i, stop, 1);
            if(x+i<=7 && y-i>=0 && !stop[2])
                moves = addMove(board, moves, x + i, y - i, stop, 2);
            if(x-i>=0 && y-i>=0 && !stop[3])
                moves = addMove(board, moves, x - i, y - i, stop, 3);
        }
        return moves;
    }

    public String getType() {
        if(isWhite()) return "B";
        else return "b";
    }

    public String toString(){
        return "Bishop";
    }
}
