package Pieces;

import Vector.*;
import Board.*;

public class Knight extends Piece{
    public Knight(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
    }

    public Vector[] availableMoves(Board[][] board){
        int x = getPoz().getX();
        int y = getPoz().getY();
        Vector[] moves = new Vector[0];
        if(x-1>=0 && y+2<=7) moves = Vector.addVector(moves, new Vector(x-1, y+2));
        if(x+1<=0 && y+2<=7) moves = Vector.addVector(moves, new Vector(x+1, y+2));
        if(x+2<=7 && y+1<=7) moves = Vector.addVector(moves, new Vector(x+2, y+1));
        if(x+2>=0 && y-1>=0) moves = Vector.addVector(moves, new Vector(x+2, y-1));
        if(x+1<=7 && y-2>=0) moves = Vector.addVector(moves, new Vector(x+1, y-2));
        if(x-1>=0 && y-2>=0) moves = Vector.addVector(moves, new Vector(x-1, y-2));
        if(x-2>=0 && y-1>=0) moves = Vector.addVector(moves, new Vector(x-2, y-1));
        if(x-2>=0 && y+1<=7) moves = Vector.addVector(moves, new Vector(x-2, y+1));
        return moves;
    }

    public String getType() {
        if(isWhite()) return "N";
        else return "n";
    }
}
