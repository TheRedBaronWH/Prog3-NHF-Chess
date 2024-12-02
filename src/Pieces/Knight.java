package Pieces;

import Vector.*;
import Board.*;

import javax.swing.*;

public class Knight extends Piece{
    public Knight(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
        if(white) setIcon(new ImageIcon("src/Icons/WKnight.png"));
        else setIcon(new ImageIcon("src/Icons/BKnight.png"));
    }

    public MoveVector[] availableMoves(Board[][] board){
        int x = getPoz().getX();
        int y = getPoz().getY();
        MoveVector[] moves = new MoveVector[0];
        if(x-1>=0 && y+2<=7) moves = addMoveSingle(board, moves, x - 1, y + 2);
        if(x+1<=0 && y+2<=7) moves = addMoveSingle(board, moves, x + 1, y + 2);
        if(x+2<=7 && y+1<=7) moves = addMoveSingle(board, moves, x + 2, y + 1);
        if(x+2>=0 && y-1>=0) moves = addMoveSingle(board, moves, x + 2, y - 1);
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
}
