package Vector;

public class MoveVector extends Vector{
    private int takeable;

    public MoveVector(int x, int y, int takeable){
        super(x,y);
        this.takeable = takeable;
    }

    public boolean isTakeable(){
        return takeable == 2;
    }

    public static MoveVector[] addVector(MoveVector[] moves, MoveVector vector) {
        MoveVector[] temp = new MoveVector[moves.length + 1];
        for (int i = 0; i < moves.length; i++) {
            temp[i] = moves[i];
        }
        temp[temp.length - 1] = vector;
        return temp;
    }
}
