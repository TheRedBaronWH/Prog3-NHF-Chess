package Model.Vector;

public class MoveVector extends Vector{
    private int takeable;

    public MoveVector(int x, int y, int takeable){
        super(x,y);
        this.takeable = takeable;
    }

    public int getTakeable(){
        return takeable;
    }

    public static MoveVector[] addVector(MoveVector[] moves, MoveVector vector) {
        MoveVector[] temp = new MoveVector[moves.length + 1];
        System.arraycopy(moves, 0, temp, 0, moves.length);
        temp[temp.length - 1] = vector;return temp;
    }

    public static boolean contains(MoveVector[] moves, int x, int y) {
        for (MoveVector move : moves) {
            if (move.getX() == x && move.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public static int moveType(MoveVector[] moves, int x, int y){
        if(contains(moves, x, y)){
            for (MoveVector move : moves) {
                if (move.getX() == x && move.getY() == y) {
                    return move.getTakeable();
                }
            }
        }
        return 0;
    }
}
