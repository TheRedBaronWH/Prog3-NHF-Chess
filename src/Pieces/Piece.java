package Pieces;

import Vector.*;
import Board.*;

public abstract class Piece {
    protected Vector poz;
    protected boolean white;
    protected boolean hasmoved;

    public void setWhite(boolean white){
        this.white = white;
    }

    public void setPoz(Vector poz){
        this.poz = poz;
    }

    public boolean isWhite(){
        return white;
    }

    public Vector getPoz() {
        return poz;
    }

    public boolean hasMoved() {
        return hasmoved;
    }

    public MoveVector[] addMove(Board[][] board, MoveVector[] moves, int x, int y, Boolean[] stop, int i){
        if(board[x][y].getPiece()!=null){
            if(board[x][y].getPiece().isWhite()!=this.isWhite()) {
                moves = MoveVector.addVector(moves, new MoveVector(x, y, 2));
            }
            stop[i] = true;
        }
        else moves = MoveVector.addVector(moves, new MoveVector(x, y, 1));
        return moves;
    }

    public MoveVector[] addMoveSingle(Board[][] board, MoveVector[] moves, int x, int y){
        if(board[x][y].getPiece()!=null){
            if(board[x][y].getPiece().isWhite()!=this.isWhite()) {
                moves = MoveVector.addVector(moves, new MoveVector(x, y, 2));
            }
        }
        else moves = MoveVector.addVector(moves, new MoveVector(x, y, 1));
        return moves;
    }

    public abstract String getType();
    public abstract MoveVector[] availableMoves(Board[][] board);
}
