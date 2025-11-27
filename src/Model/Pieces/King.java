package Model.Pieces;

import Model.BoardManager;
import Model.BoardTile;
import Model.Vector.MoveVector;
import Model.Vector.Vector;

import javax.swing.*;

public class King extends Piece{
    public King(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
        if(white) setIcon(new ImageIcon(iconSource + "WKing.png"));
        else setIcon(new ImageIcon(iconSource + "BKing.png"));
    }

    public MoveVector[] sanc(BoardTile[][] boardTile, MoveVector[] moves, int x, int y){
        boolean good;
        if(!hasMoved()){
            good = true;
            for(int i=1; i<4; i++){
                if(boardTile[x][i].getPiece()!=null) good = false;
            }
            if(good && !boardTile[x][0].getPiece().hasMoved()) moves = addMoveSingle(boardTile, moves, x, 0, true);
            good = true;
            for(int i=5; i<7; i++){
                if(boardTile[x][i].getPiece()!=null) good = false;
            }
            if(good && !boardTile[x][7].getPiece().hasMoved()) moves = addMoveSingle(boardTile, moves, x, 7, true);
        }
        return moves;
    }

    MoveVector[] availableMoves(){
        BoardTile[][] board = BoardManager.getBoard();
        if(isWhite() != BoardManager.getWhiteTurn()) return new MoveVector[0];
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
