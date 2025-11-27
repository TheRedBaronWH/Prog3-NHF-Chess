package Model.Pieces;

import Model.BoardManager;
import Model.BoardTile;
import Model.Vector.MoveVector;
import Model.Vector.Vector;

import javax.swing.*;

public class Knight extends Piece{
    public Knight(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
        value = 3;
        if(white) setIcon(new ImageIcon(iconSource + "WKnight.png"));
        else setIcon(new ImageIcon(iconSource + "BKnight.png"));
    }

    MoveVector[] availableMoves(){
        BoardTile[][] board = BoardManager.getBoard();
        if(isWhite() != BoardManager.getWhiteTurn()) return new MoveVector[0];
        int x = getPoz().getX();
        int y = getPoz().getY();
        MoveVector[] moves = new MoveVector[0];
        if(x-1>=0 && y+2<=7) moves = addMoveSingle(board, moves, x - 1, y + 2);
        if(x+1<=0 && y+2<=7) moves = addMoveSingle(board, moves, x + 1, y + 2);
        if(x+2<=7 && y+1<=7) moves = addMoveSingle(board, moves, x + 2, y + 1);
        if(x+2<=7 && y-1>=0) moves = addMoveSingle(board, moves, x + 2, y - 1);
        if(x+1<=7 && y-2>=0) moves = addMoveSingle(board, moves, x + 1, y - 2);
        if(x-1>=0 && y-2>=0) moves = addMoveSingle(board, moves, x - 1, y - 2);
        if(x-2>=0 && y-1>=0) moves = addMoveSingle(board, moves, x - 2, y - 1);
        if(x-2>=0 && y+1<=7) moves = addMoveSingle(board, moves, x - 2, y + 1);
        return moves;
    }

    public String getType() {
        if(isWhite()) return "N";
        else return "n";
    }

    public String toString(){
        return "Knight";
    }
}
