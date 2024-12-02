package Pieces;

import Vector.*;
import Board.*;

import javax.swing.*;

public class Pawn extends Piece {
    private boolean doubleStep = false;

    public Pawn(Vector poz, boolean white) {
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
        if(white) setIcon(new ImageIcon("src/Icons/WPawn.png"));
        else setIcon(new ImageIcon("src/Icons/BPawn.png"));
    }

    @Override
    public boolean wasDoubleStep() {
        return doubleStep;
    }

    public MoveVector[] availableMoves(Board[][] board) {
        int x = getPoz().getX();
        int y = getPoz().getY();
        MoveVector[] moves = new MoveVector[0];
        moves = addMoveSingle(board, moves, x, y);
        return moves;
    }

    public MoveVector[] addMoveSingle(Board[][] board, MoveVector[] moves, int x, int y) {
        int i = 1;
        if (isWhite()) i = -1;
        if(x+i>=0 && x+i<=7 && y-1>=0 && board[x+i][y-1].getPiece()!= null && board[x+i][y-1].getPiece().isWhite()!=this.isWhite())
            moves = MoveVector.addVector(moves, new MoveVector(x+i,y-1,2));
        if(x+i>=0 && x+i<=7 && y+1<=7 && board[x+i][y+1].getPiece()!= null && board[x+i][y+1].getPiece().isWhite()!=this.isWhite())
            moves = MoveVector.addVector(moves, new MoveVector(x+i,y+1,2));
        if (x + i < 7 && x + i > 0) {
            if (board[x + i][y].getPiece() == null) {
                if (doubleStep) doubleStep = false;
                moves = MoveVector.addVector(moves, new MoveVector(x + i, y, 1));
                if (!hasMoved() && board[x + 2 * i][y].getPiece() == null) {
                    moves = MoveVector.addVector(moves, new MoveVector(x + 2 * i, y, 1));
                    doubleStep = true;
                }
            }
            else {
                if ((board[x + i][y].getPiece().getType().equals("p") || board[x + i][y].getPiece().getType().equals("P")) && board[x + i][y].getPiece().wasDoubleStep()) {
                    if (y - 1 > 0) {
                        if (board[x + i][y - 1].getPiece() == null) moves = MoveVector.addVector(moves, new MoveVector(x + i, y - 1, 5));
                    }
                    if (y + 1 < 8) {
                        if (board[x + i][y + 1].getPiece() == null) moves = MoveVector.addVector(moves, new MoveVector(x + i, y + 1, 6));
                    }
                }
            }
        }
        else if((x + i == 7 || x + i == 0) && board[x+i][y].getPiece()==null)  moves = MoveVector.addVector(moves, new MoveVector(x+i, y, 7));
        return moves;
    }

    public String getType(){
        if(isWhite()) return "P";
        else return "p";
    }
}
