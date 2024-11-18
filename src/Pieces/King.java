package Pieces;

import Vector.*;
import Board.*;

public class King extends Piece{
    public King(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
    }

    public Vector[] availableMoves(Board[][] board){
        int x = getPoz().getX();
        int y = getPoz().getY();
        Vector[] moves = new Vector[0];
        if(x-1>=0) moves = Vector.addVector(moves, new Vector(x -1 , y + 0));
        if(x-1>=0 && y+1<=7) moves = Vector.addVector(moves, new Vector(x - 1, y + 1));
        if(y+1<=7) moves = Vector.addVector(moves, new Vector(x + 0, y + 1));
        if(x+1<=7 && y+1<=7) moves = Vector.addVector(moves, new Vector(x + 1, y + 1));
        if(x+1<=7) moves = Vector.addVector(moves, new Vector(x + 1, y + 0));
        if(x+1<=7 && y-1>=0) moves = Vector.addVector(moves, new Vector(x + 1, y - 1));
        if(y-1>=0) moves = Vector.addVector(moves, new Vector(x + 0, y - 1));
        if(x-1>=0 && y-1>=0) moves = Vector.addVector(moves, new Vector(x - 1, y - 1));
        return moves;
    }

    public String getType() {
        if(isWhite()) return "K";
        else return "k";
    }
}
