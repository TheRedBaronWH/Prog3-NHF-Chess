package Model.Pieces;

import Model.BoardTile;
import Model.MoveTypes;
import Model.Vector.MoveVector;
import Model.Vector.Vector;

import javax.swing.*;

public abstract class Piece {
    protected Vector poz;
    protected boolean white;
    protected boolean hasmoved;
    protected ImageIcon icon;
    protected int value;
    protected String iconSource = "src/Resources/";

    public void setWhite(boolean white){
        this.white = white;
    }

    public void setPoz(Vector poz){
        this.poz = poz;
    }

    public void setHasMoved(){ this.hasmoved = true; }

    public void setIcon(ImageIcon icon){ this.icon = icon; }

    public boolean isWhite(){
        return white;
    }

    public Vector getPoz() {
        return poz;
    }

    public boolean hasMoved() { return hasmoved; }

    public ImageIcon getIcon() { return icon; }

    public int getValue() { return value; }

    public MoveVector[] addMove(BoardTile[][] boardTile, MoveVector[] moves, int x, int y, Boolean[] stop, int i){
        if(boardTile[x][y].getPiece()!=null){
            if(boardTile[x][y].getPiece().isWhite()!=this.isWhite()) {
                if((this.isWhite() && boardTile[x][y].getPiece().getType().equals("k"))
                        || (!this.isWhite() && boardTile[x][y].getPiece().getType().equals("K")))
                {
                    moves = MoveVector.addVector(moves, new MoveVector(x, y, MoveTypes.KING_TAKEN));
                }
                else moves = MoveVector.addVector(moves, new MoveVector(x, y, MoveTypes.PIECE_TAKEN));
            }
            else{
                if((!boardTile[x][y].getPiece().hasMoved() && !this.hasMoved()) &&
                        boardTile[x][y].getPiece().getType().equals("K") && this.getType().equals("R") ||
                        boardTile[x][y].getPiece().getType().equals("k") && this.getType().equals("r") ||
                        boardTile[x][y].getPiece().getType().equals("R") && this.getType().equals("K") ||
                        boardTile[x][y].getPiece().getType().equals("r") && this.getType().equals("k")
                ){
                    moves = MoveVector.addVector(moves, new MoveVector(x, y, MoveTypes.KING_ROOK_SWITCH));
                }
            }
            stop[i] = true;
        }
        else moves = MoveVector.addVector(moves, new MoveVector(x, y, MoveTypes.NO_CONSEQUENCE));
        return moves;
    }

    public MoveVector[] addMoveSingle(BoardTile[][] boardTile, MoveVector[] moves, int x, int y){
        if(boardTile[x][y].getPiece()!=null){
            if(boardTile[x][y].getPiece().isWhite()!=this.isWhite()) {
                moves = MoveVector.addVector(moves, new MoveVector(x, y, MoveTypes.PIECE_TAKEN));
            }
        }
        else moves = MoveVector.addVector(moves, new MoveVector(x, y, MoveTypes.NO_CONSEQUENCE));
        return moves;
    }

    public MoveVector[] addMoveSingle(BoardTile[][] boardTile, MoveVector[] moves, int x, int y, boolean bypass){
        if(boardTile[x][y].getPiece()!=null){
            if(boardTile[x][y].getPiece().isWhite()!=this.isWhite()) {
                moves = MoveVector.addVector(moves, new MoveVector(x, y, MoveTypes.PIECE_TAKEN));
            }
            if(bypass){
                moves = MoveVector.addVector(moves, new MoveVector(x, y, MoveTypes.KING_ROOK_SWITCH));
            }
        }
        else moves = MoveVector.addVector(moves, new MoveVector(x, y, MoveTypes.NO_CONSEQUENCE));
        return moves;
    }

    public boolean wasDoubleStep() { return false; }
    public abstract String getType();
    public abstract String toString();
    public abstract MoveVector[] availableMoves(BoardTile[][] boardTile);
}
