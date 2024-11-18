package Pieces;

import Vector.*;
import Board.*;

public class Queen extends Piece {
    public Queen(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
    }

    public Vector[] availableMoves(Board[][] board){
        boolean[] stop = new boolean[8];
        for(int i=0; i<8; i++){
            stop[i] = false;
        }
        int x = getPoz().getX();
        int y = getPoz().getY();
        Vector[] moves = new Vector[0];
        for(int i = 1; i < 8; i++){
            if(x-i>=0 && stop[1]==false) {
                moves = Vector.addVector(moves, new Vector(x-i, y+0));
                if(board[x-i][y].getPiece()!=null) stop[1]=true;
            }
            if(x-i>=0 && y+i<=7 && stop[2]==false) {
                moves = Vector.addVector(moves, new Vector(x-i, y+i));
                if(board[x-i][y+i].getPiece()!=null) stop[2]=true;
            }
            if(y+i<=7 && stop[3]==false) {
                moves = Vector.addVector(moves, new Vector(x+0, y+i));
                if(board[x][y+i].getPiece()!=null) stop[3]=true;
            }
            if(x+i<=7 && y+i<=7 && stop[4]==false) {
                moves = Vector.addVector(moves, new Vector(x+i, y+i));
                if(board[x+i][y+i].getPiece()!=null) stop[4]=true;
            }
            if(x+i<=7 && stop[5]==false) {
                moves = Vector.addVector(moves, new Vector(x+i, y+0));
                if(board[x+i][y].getPiece()!=null) stop[5]=true;
            }
            if(x+i<=7 && y-i>=0 && stop[6]==false) {
                moves = Vector.addVector(moves, new Vector(x+i, y-i));
                if(board[x+i][y-i].getPiece()!=null) stop[6]=true;
            }
            if(y-i>=0 && stop[7]==false) {
                moves =  Vector.addVector(moves, new Vector(x+0, y-i));
                if(board[x][y-i].getPiece()!=null) stop[7]=true;
            }
            if(x-i>=0 && y-i>=0 && stop[8]==false) {
                moves = Vector.addVector(moves, new Vector(x-i, y-i));
                if(board[x-i][y-i].getPiece()!=null) stop[8]=true;
            }
        }
        return moves;
    }

    public String getType() {
        if(isWhite()) return "Q";
        else return "q";
    }
}
