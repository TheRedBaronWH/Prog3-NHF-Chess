package Model.Vector;

public class Vector {
    private int x;
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Vector[] addVector(Vector[] moves, Vector vector) {
        Vector[] temp = new Vector[moves.length + 1];
        for (int i = 0; i < moves.length; i++) {
            temp[i] = moves[i];
        }
        temp[temp.length - 1] = vector;
        return temp;
    }

    public static Vector[] copyVector(Vector[] old) {
        Vector[] temp = new Vector[old.length];
        for (int i = 0; i < old.length; i++) {
            temp[i] = old[i];
        }
        return temp;
    }
}
