package Model.Pieces;

import Model.BoardManager;
import Model.BoardTile;
import Model.MoveTypes;
import Model.Vector.MoveVector;
import Model.Vector.Vector;

import javax.swing.*;

public class Pawn extends Piece {
    private boolean doubleStep = false;

    public Pawn(Vector poz, boolean white) {
        setPoz(poz);
        setWhite(white);
        hasmoved = false;
        value = 1;
        if(white) setIcon(new ImageIcon(iconSource + "WPawn.png"));
        else setIcon(new ImageIcon(iconSource + "BPawn.png"));
    }

    @Override
    public boolean wasDoubleStep() {
        return doubleStep;
    }

    MoveVector[] availableMoves() {
        BoardTile[][] board = BoardManager.getBoard();
        if(isWhite() != BoardManager.getWhiteTurn()) return new MoveVector[0];
        int x = getPoz().getX();
        int y = getPoz().getY();
        MoveVector[] moves = new MoveVector[0];
        moves = addMoveSingle(board, moves, x, y);
        return moves;
    }

    public MoveVector[] addMoveSingle(BoardTile[][] boardTile, MoveVector[] moves, int x, int y) {
        int i = 1;
        if (isWhite()) i = -1;
        if(x+i>=0 && x+i<=7 && y-1>=0 && boardTile[x+i][y-1].getPiece()!= null && boardTile[x+i][y-1].getPiece().isWhite()!=this.isWhite())
            moves = MoveVector.addVector(moves, new MoveVector(x+i,y-1, MoveTypes.PIECE_TAKEN));
        if(x+i>=0 && x+i<=7 && y+1<=7 && boardTile[x+i][y+1].getPiece()!= null && boardTile[x+i][y+1].getPiece().isWhite()!=this.isWhite())
            moves = MoveVector.addVector(moves, new MoveVector(x+i,y+1,MoveTypes.PIECE_TAKEN));
        if (x + i < 7 && x + i > 0) {
            if (boardTile[x + i][y].getPiece() == null) {
                if (doubleStep) doubleStep = false;
                moves = MoveVector.addVector(moves, new MoveVector(x + i, y, MoveTypes.NO_CONSEQUENCE));
                if (!hasMoved() && boardTile[x + 2 * i][y].getPiece() == null) {
                    moves = MoveVector.addVector(moves, new MoveVector(x + 2 * i, y, MoveTypes.NO_CONSEQUENCE));
                    doubleStep = true;
                }
            } else {
                if (x - 1 > 0 && boardTile[x - 1][y].getPiece() != null) {
                    if ((boardTile[x - 1][y].getPiece().getType().equals("p") || boardTile[x - 1][y].getPiece().getType().equals("P"))
                            && boardTile[x - 1][y].getPiece().wasDoubleStep()) {
                        if (boardTile[x - 1][y + i].getPiece() == null)
                            moves = MoveVector.addVector(moves, new MoveVector(x - 1, y + i, MoveTypes.PAWN_ENPASSANT_L));
                    }
                }
                if (x + 1 < 8 && boardTile[x + 1][y].getPiece() != null) {
                    if ((boardTile[x + 1][y].getPiece().getType().equals("p") || boardTile[x + 1][y].getPiece().getType().equals("P"))
                            && boardTile[x + 1][y].getPiece().wasDoubleStep()) {
                        if (boardTile[x + 1][y + i].getPiece() == null)
                            moves = MoveVector.addVector(moves, new MoveVector(x + 1, y + i, MoveTypes.PAWN_ENPASSANT_R));
                    }
                }
            }
        }
        if((x + i == 7 || x + i == 0) && boardTile[x+i][y].getPiece()==null)  moves = MoveVector.addVector(moves, new MoveVector(x+i, y, MoveTypes.PAWN_SWITCH));
        return moves;
    }

    public String getType(){
        if(isWhite()) return "P";
        else return "p";
    }

    public String toString(){
        return "Pawn";
    }
}
