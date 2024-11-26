package Vector;

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
        for (int i = 0; i < moves.length; i++) {
            temp[i] = moves[i];
        }
        temp[temp.length - 1] = vector;
        return temp;
    }

    public static boolean contains(MoveVector[] moves, int x, int y) {
        for(int i = 0; i < moves.length; i++){
            if(moves[i].getX() == x && moves[i].getY() == y){
                return true;
            }
        }
        return false;
    }

    public static int moveType(MoveVector[] moves, int x, int y){
        if(contains(moves, x, y)){
            for(int i = 0; i < moves.length; i++){
                if(moves[i].getX() == x && moves[i].getY() == y){
                    return moves[i].getTakeable();
                }
            }
        }
        return 0;
    }
}
