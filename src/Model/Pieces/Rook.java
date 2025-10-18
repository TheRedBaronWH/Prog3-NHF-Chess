package Model.Pieces;

import Model.BoardTile;
import Model.Vector.MoveVector;
import Model.Vector.Vector;
import View.Screens.GameScreen;

import javax.swing.*;

public class Rook extends Piece {
    public Rook(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
        value = 5;
        if(white) setIcon(new ImageIcon(iconSource + "WRook.png"));
        else setIcon(new ImageIcon(iconSource + "BRook.png"));
    }

    public MoveVector[] sanc(BoardTile[][] boardTile, MoveVector[] moves, int x, int y){
        if(!hasMoved()){
            if(y == 0) {
                for (int i = 1; i < 4; i++) {
                    if (boardTile[x][i].getPiece() != null) return moves;
                }
            }
            if(y==7) {
                for (int i = 5; i < 7; i++) {
                    if (boardTile[x][i].getPiece() != null) return moves;
                }
            }
            if(!boardTile[x][4].getPiece().hasMoved()) moves = addMoveSingle(boardTile, moves, x, 4, true);
            return moves;
        }
        return moves;
    }

    public MoveVector[] availableMoves(BoardTile[][] boardTile){
        if(isWhite() != GameScreen.getWhiteTurn()) return new MoveVector[0];
        int x = getPoz().getX();
        int y = getPoz().getY();
        MoveVector[] moves = new MoveVector[0];
        //stores if it should stop or continue searching in the given direction
        Boolean[] stop = new Boolean[4];
        for(int i = 0; i<4; i++){
            stop[i] = false;
        }
        for(int i = 1; i < 8; i++){
            if(x-i>=0 && !stop[0])
                moves = addMove(boardTile, moves, x - i, y, stop, 0);
            if(y+i<=7 && !stop[1])
                if(boardTile[x][y+i].getPiece() != null) {
                    if (!boardTile[x][y + i].getPiece().getType().equals("K") && !boardTile[x][y + i].getPiece().getType().equals("k"))
                        moves = addMove(boardTile, moves, x, y + i, stop, 1);
                }
                else moves = addMove(boardTile, moves, x, y + i, stop, 1);
            if(x+i<=7 && !stop[2])
                moves = addMove(boardTile, moves, x + i, y, stop, 2);
            if(y-i>=0 && !stop[3])
                if(boardTile[x][y-i].getPiece() != null) {
                    if (!boardTile[x][y - i].getPiece().getType().equals("K") && !boardTile[x][y - i].getPiece().getType().equals("k"))
                        moves = addMove(boardTile, moves, x, y - i, stop, 3);
                }
                else moves = addMove(boardTile, moves, x, y - i, stop, 3);

        }
        if(!hasMoved()) moves = sanc(boardTile, moves, x, y);
        return moves;
    }

    public String getType() {
        if(isWhite()) return "R";
        else return "r";
    }

    public String toString(){
        return "Rook";
    }
}
