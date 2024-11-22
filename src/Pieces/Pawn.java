package Pieces;

import Vector.*;
import Board.*;

public class Pawn extends Piece{
    public Pawn(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
    }

    public MoveVector[] availableMoves(Board[][] board){
        int x = getPoz().getX();
        int y = getPoz().getY();
        MoveVector[] moves = new MoveVector[0];
        if(isWhite()) {
            if (y - 1 >= 0) moves = addMoveSingle(board, moves, x - 1, y - 1);
            moves = addMoveSingle(board, moves, x - 1, y + 0);
            if (y + 1 <= 7) moves = addMoveSingle(board, moves, x - 1, y + 1);
        }
        else{
            if (y - 1 >= 0) moves = addMoveSingle(board, moves, x + 1, y - 1);
            moves = addMoveSingle(board, moves, x + 1, y + 0);
            if (y + 1 <= 7) moves = addMoveSingle(board, moves, x + 1, y + 1);
        }
        return moves;
    }

    public String getType() {
        if(isWhite()) return "P";
        else return "p";
    }
}
