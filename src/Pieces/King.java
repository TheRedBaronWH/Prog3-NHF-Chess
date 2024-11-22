package Pieces;

import Vector.*;
import Board.*;

public class King extends Piece{
    public King(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
    }

    public MoveVector[] availableMoves(Board[][] board){
        int x = getPoz().getX();
        int y = getPoz().getY();
        MoveVector[] moves = new MoveVector[0];
        if(x-1>=0) moves = addMoveSingle(board, moves, x - 1, y + 0);
        if(x-1>=0 && y+1<=7) moves = addMoveSingle(board, moves, x - 1, y + 1);
        if(y+1<=7) moves = addMoveSingle(board, moves, x + 0, y + 1);
        if(x+1<=7 && y+1<=7) moves = addMoveSingle(board, moves, x + 1, y + 1);
        if(x+1<=7) moves = addMoveSingle(board, moves, x + 1, y + 0);
        if(x+1<=7 && y-1>=0) moves = addMoveSingle(board, moves, x + 1, y - 1);
        if(y-1>=0) moves = addMoveSingle(board, moves, x + 0, y - 1);
        if(x-1>=0 && y-1>=0) moves = addMoveSingle(board, moves, x - 1, y - 1);
        return moves;
    }

    public String getType() {
        if(isWhite()) return "K";
        else return "k";
    }
}
