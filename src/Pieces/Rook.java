package Pieces;

import Vector.*;
import Board.*;

public class Rook extends Piece {
    public Rook(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
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
        return moves;
    }

    public String getType() {
        if(isWhite()) return "R";
        else return "r";
    }
}
