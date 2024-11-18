package Pieces;

import Vector.*;
import Board.*;

public abstract class Piece {
    protected Vector poz;
    protected boolean white;
    protected boolean hasmoved;

    public void setWhite(boolean white){
        this.white = white;
    }

    public void setPoz(Vector poz){
        this.poz = poz;
    }

    public boolean isWhite(){
        return white;
    }

    public Vector getPoz() {
        return poz;
    }

    public boolean hasMoved() {
        return hasmoved;
    }

    public abstract String getType();
    public abstract Vector[] availableMoves(Board[][] board);
}
