package Pieces;

import Vector.*;
import Board.*;

import javax.swing.*;

public class Bishop extends Piece {
    public Bishop(Vector poz, boolean white){
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
        if(white) setIcon(new ImageIcon("src/Icons/WBishop.png"));
        else setIcon(new ImageIcon("src/Icons/BBishop.png"));
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
            if(x-i>=0 && y+i<=7 && !stop[0])
                moves = addMove(board, moves, x - i, y + i, stop, 0);
            if(x+i<=7 && y+i<=7 && !stop[1])
                moves = addMove(board, moves, x + i, y + i, stop, 1);
            if(x+i<=7 && y-i>=0 && !stop[2])
                moves = addMove(board, moves, x + i, y - i, stop, 2);
            if(x-i>=0 && y-i>=0 && !stop[3])
                moves = addMove(board, moves, x - i, y - i, stop, 3);
        }
        return moves;
    }

    public String getType() {
        if(isWhite()) return "B";
        else return "b";
    }
}
