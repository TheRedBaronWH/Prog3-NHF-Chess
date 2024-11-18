package Pieces;

import Vector.*;
import Board.*;

public class Pawn extends Piece{
    public Pawn(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
    }

    public Vector[] availableMoves(Board[][] board){
        int x = getPoz().getX();
        int y = getPoz().getY();
        Vector[] moves = new Vector[0];
        if(isWhite()) {
            if (y - 1 >= 0) moves = Vector.addVector(moves, new Vector(x - 1, y - 1));
            moves = Vector.addVector(moves, new Vector(x - 1, y + 0));
            if (y + 1 <= 7) moves = Vector.addVector(moves, new Vector(x - 1, y + 1));
        }
        else{
            if (y - 1 >= 0) moves = Vector.addVector(moves, new Vector(x + 1, y - 1));
            moves = Vector.addVector(moves, new Vector(x + 1, y + 0));
            if (y + 1 <= 7) moves = Vector.addVector(moves, new Vector(x + 1, y + 1));
        }
        return moves;
    }

    public String getType() {
        if(isWhite()) return "P";
        else return "p";
    }
}
