package Model.Pieces;

import Model.BoardTile;
import Model.Vector.MoveVector;
import Model.Vector.Vector;
import View.Screens.GameScreen;

import javax.swing.*;

public class Queen extends Piece {
    public Queen(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
        value = 9;
        if(white) setIcon(new ImageIcon(iconSource + "WQueen.png"));
        else setIcon(new ImageIcon(iconSource + "BQueen.png"));
    }

    public MoveVector[] availableMoves(BoardTile[][] boardTile){
        if(isWhite() != GameScreen.getWhiteTurn()) return new MoveVector[0];
        int x = getPoz().getX();
        int y = getPoz().getY();
        MoveVector[] moves = new MoveVector[0];
        Boolean[] stop = new Boolean[8];
        for(int i = 0; i<8; i++){
            stop[i] = false;
        }
        for(int i = 1; i < 8; i++){
            if(x-i>=0 && y+i<=7 && !stop[0])
                moves = addMove(boardTile, moves, x - i, y + i, stop, 0);
            if(x+i<=7 && y+i<=7 && !stop[1])
                moves = addMove(boardTile, moves, x + i, y + i, stop, 1);
            if(x+i<=7 && y-i>=0 && !stop[2])
                moves = addMove(boardTile, moves, x + i, y - i, stop, 2);
            if(x-i>=0 && y-i>=0 && !stop[3])
                moves = addMove(boardTile, moves, x - i, y - i, stop, 3);
            if(x-i>=0 && !stop[4])
                moves = addMove(boardTile, moves, x - i, y, stop, 4);
            if(y+i<=7 && !stop[5])
                moves = addMove(boardTile, moves, x, y + i, stop, 5);
            if(x+i<=7 && !stop[6])
                moves = addMove(boardTile, moves, x + i, y, stop, 6);
            if(y-i>=0 && !stop[7])
                moves = addMove(boardTile, moves, x, y - i, stop, 7);
        }
        return moves;
    }

    public String getType() {
        if(isWhite()) return "Q";
        else return "q";
    }

    public String toString(){
        return "Queen";
    }
}
