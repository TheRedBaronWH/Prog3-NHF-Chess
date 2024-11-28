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

    public void setHasMoved(){ this.hasmoved = true; }

    public boolean isWhite(){
        return white;
    }

    public Vector getPoz() {
        return poz;
    }

    public boolean hasMoved() { return hasmoved; }

    public MoveVector[] addMove(Board[][] board, MoveVector[] moves, int x, int y, Boolean[] stop, int i){
        if(board[x][y].getPiece()!=null){
            if(board[x][y].getPiece().isWhite()!=this.isWhite()) {
                if((this.isWhite() && board[x][y].getPiece().getType().equals("k")) || (!this.isWhite() && board[x][y].getPiece().getType().equals("K"))){
                    moves = MoveVector.addVector(moves, new MoveVector(x, y, 3));
                }
                else moves = MoveVector.addVector(moves, new MoveVector(x, y, 2));
            }
            else{
                if(board[x][y].getPiece().getType().equals("K") && this.getType().equals("R") ||
                        board[x][y].getPiece().getType().equals("k") && this.getType().equals("r") ||
                        board[x][y].getPiece().getType().equals("R") && this.getType().equals("K") ||
                        board[x][y].getPiece().getType().equals("r") && this.getType().equals("k")
                ){
                    moves = MoveVector.addVector(moves, new MoveVector(x, y, 4));
                }
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

    public MoveVector[] addMoveSingle(Board[][] board, MoveVector[] moves, int x, int y, boolean bypass){
        if(board[x][y].getPiece()!=null){
            if(board[x][y].getPiece().isWhite()!=this.isWhite()) {
                moves = MoveVector.addVector(moves, new MoveVector(x, y, 2));
            }
            if(bypass){
                moves = MoveVector.addVector(moves, new MoveVector(x, y, 4));
            }
        }
        else moves = MoveVector.addVector(moves, new MoveVector(x, y, 1));
        return moves;
    }

    public boolean wasDoubleStep() { return false; }
    public abstract String getType();
    public abstract MoveVector[] availableMoves(Board[][] board);
}
