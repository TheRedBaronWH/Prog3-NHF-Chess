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
            moves = addMoveSingle(board, moves, x - 1, y);
            moves = addMoveSingle(board, moves, x - 2, y);
        }
        else{
            moves = addMoveSingle(board, moves, x + 1, y);
            moves = addMoveSingle(board, moves, x + 2, y);
        }
        return moves;
    }

    public String getType() {
        if(isWhite()) return "P";
        else return "p";
    }
}
