package Pieces;

import Vector.*;
import Board.*;

import javax.swing.*;

public class King extends Piece{
    public King(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
        if(white) setIcon(new ImageIcon("src/Icons/WKing.png"));
        else setIcon(new ImageIcon("src/Icons/BKing.png"));
    }

    public MoveVector[] sanc(Board[][] board, MoveVector[] moves, int x, int y){
        boolean good;
        if(!hasMoved()){
            good = true;
            for(int i=1; i<4; i++){
                if(board[x][i].getPiece()!=null) good = false;
            }
            if(good && !board[x][0].getPiece().hasMoved()) moves = addMoveSingle(board, moves, x, 0, true);
            good = true;
            for(int i=5; i<7; i++){
                if(board[x][i].getPiece()!=null) good = false;
            }
            if(good && !board[x][7].getPiece().hasMoved()) moves = addMoveSingle(board, moves, x, 7, true);
        }
        return moves;
    }

    public MoveVector[] availableMoves(Board[][] board){
        int x = getPoz().getX();
        int y = getPoz().getY();
        MoveVector[] moves = new MoveVector[0];
        if(x-1>=0) moves = addMoveSingle(board, moves, x - 1, y);
        if(x-1>=0 && y+1<=7) moves = addMoveSingle(board, moves, x - 1, y + 1);
        if(y+1<=7) moves = addMoveSingle(board, moves, x, y + 1);
        if(x+1<=7 && y+1<=7) moves = addMoveSingle(board, moves, x + 1, y + 1);
        if(x+1<=7) moves = addMoveSingle(board, moves, x + 1, y);
        if(x+1<=7 && y-1>=0) moves = addMoveSingle(board, moves, x + 1, y - 1);
        if(y-1>=0) moves = addMoveSingle(board, moves, x, y - 1);
        if(x-1>=0 && y-1>=0) moves = addMoveSingle(board, moves, x - 1, y - 1);
        if(!hasMoved()) moves = sanc(board, moves, x, y);
        return moves;
    }

    public String getType() {
        if(isWhite()) return "K";
        else return "k";
    }

    public String toString(){
        return "King";
    }
}
