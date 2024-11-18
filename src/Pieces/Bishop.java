package Pieces;

import Vector.*;
import Board.*;

public class Bishop extends Piece {
    public Bishop(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
    }

    public Vector[] availableMoves(Board[][] board){
        int x = getPoz().getX();
        int y = getPoz().getY();
        Vector[] moves = new Vector[0];
        for(int i = 1; i < 8; i++){
            if(x-i>=0 && y+i<=7) moves = Vector.addVector(moves, new Vector(x-i, y+i));
            if(x+i<=7 && y+i<=7) moves = Vector.addVector(moves, new Vector(x+i, y+i));
            if(x+i<=7 && y-i>=0) moves = Vector.addVector(moves, new Vector(x+i, y-i));
            if(x-i>=0 && y-i>=0) moves = Vector.addVector(moves, new Vector(x-i, y-i));
        }
        return moves;
    }

    public String getType() {
        if(isWhite()) return "B";
        else return "b";
    }
}
