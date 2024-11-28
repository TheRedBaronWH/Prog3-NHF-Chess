package Pieces;

import Vector.*;
import Board.*;

public class Rook extends Piece {
    public Rook(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
    }

    public MoveVector[] sanc(Board[][] board, MoveVector[] moves, int x, int y){
        if(!hasMoved()){
            if(y == 0) {
                for (int i = 1; i < 4; i++) {
                    if (board[x][i].getPiece() != null) return moves;
                }
            }
            if(y==7) {
                for (int i = 5; i < 7; i++) {
                    if (board[x][i].getPiece() != null) return moves;
                }
            }
            if(!board[x][4].getPiece().hasMoved()) moves = addMoveSingle(board, moves, x, 4, true);
            return moves;
        }
        return moves;
    }

    public MoveVector[] availableMoves(Board[][] board){
        int x = getPoz().getX();
        int y = getPoz().getY();
        MoveVector[] moves = new MoveVector[0];
        Boolean[] stop = new Boolean[4];
        for(int i = 0; i<4; i++){
            stop[i] = false;
        }
        for(int i = 1; i < 8; i++){
            if(x-i>=0 && !stop[0])
                moves = addMove(board, moves, x - i, y, stop, 0);
            if(y+i<=7 && !stop[1])
                moves = addMove(board, moves, x, y + i, stop, 1);
            if(x+i<=7 && !stop[2])
                moves = addMove(board, moves, x + i, y, stop, 2);
            if(y-i>=0 && !stop[3])
                moves = addMove(board, moves, x, y - i, stop, 3);
        }
        moves = sanc(board, moves, x, y);
        return moves;
    }

    public String getType() {
        if(isWhite()) return "R";
        else return "r";
    }
}
