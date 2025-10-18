import Model.BoardManager;
import Model.BoardTile;
import Model.Pieces.Bishop;
import Model.Pieces.Piece;
import Model.Pieces.Queen;
import Model.Vector.MoveVector;
import Model.Vector.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest extends BoardManager{
    private BoardTile[][] board;

    @BeforeEach
    void resetBoardBeforeTests() {
        resetBoard();
    }

    @Test
    void testBoardInit() {
        board = BoardManager.loadBoardFromFile();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                Assertions.assertEquals(i, board[i][j].getPoz().getX());
                Assertions.assertEquals(j, board[i][j].getPoz().getY());
            }
        }
        Assertions.assertEquals("r", board[0][0].getPiece().getType());
        Assertions.assertEquals("n", board[0][1].getPiece().getType());
        Assertions.assertEquals("b", board[0][2].getPiece().getType());
        Assertions.assertEquals("q", board[0][3].getPiece().getType());
        Assertions.assertEquals("k", board[0][4].getPiece().getType());
        Assertions.assertEquals("b", board[0][5].getPiece().getType());
        Assertions.assertEquals("n", board[0][6].getPiece().getType());
        Assertions.assertEquals("r", board[0][7].getPiece().getType());
        Assertions.assertEquals("R", board[7][0].getPiece().getType());
        Assertions.assertEquals("N", board[7][1].getPiece().getType());
        Assertions.assertEquals("B", board[7][2].getPiece().getType());
        Assertions.assertEquals("Q", board[7][3].getPiece().getType());
        Assertions.assertEquals("K", board[7][4].getPiece().getType());
        Assertions.assertEquals("B", board[7][5].getPiece().getType());
        Assertions.assertEquals("N", board[7][6].getPiece().getType());
        Assertions.assertEquals("R", board[7][7].getPiece().getType());
        for(int i=1; i<7; i++){
            for(int j=0; j<8; j++){
                if(i!=1 && i!=6) {
                    Assertions.assertEquals(null, board[i][j].getPiece());
                }
                if(i==1){
                    Assertions.assertEquals("p", board[i][j].getPiece().getType());
                }
                if(i==6){
                    Assertions.assertEquals("P", board[i][j].getPiece().getType());
                }

            }
        }
    }

    @Test
    void testBoardSave() {
        BoardManager.createBasicBoard();
        BoardManager.getBoardCode();
        Assertions.assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", BoardManager.getBoardCode());
    }

    @Test
    void testBoardLoadCode(){
        board = BoardManager.loadBoardFromCode("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        Assertions.assertEquals("r", board[0][0].getPiece().getType());
        Assertions.assertEquals("n", board[0][1].getPiece().getType());
        Assertions.assertEquals("b", board[0][2].getPiece().getType());
        Assertions.assertEquals("q", board[0][3].getPiece().getType());
        Assertions.assertEquals("k", board[0][4].getPiece().getType());
        Assertions.assertEquals("b", board[0][5].getPiece().getType());
        Assertions.assertEquals("n", board[0][6].getPiece().getType());
        Assertions.assertEquals("r", board[0][7].getPiece().getType());
        Assertions.assertEquals("R", board[7][0].getPiece().getType());
        Assertions.assertEquals("N", board[7][1].getPiece().getType());
        Assertions.assertEquals("B", board[7][2].getPiece().getType());
        Assertions.assertEquals("Q", board[7][3].getPiece().getType());
        Assertions.assertEquals("K", board[7][4].getPiece().getType());
        Assertions.assertEquals("B", board[7][5].getPiece().getType());
        Assertions.assertEquals("N", board[7][6].getPiece().getType());
        Assertions.assertEquals("R", board[7][7].getPiece().getType());
        for(int i=1; i<7; i++){
            for(int j=0; j<8; j++){
                if(i!=1 && i!=6) {
                    Assertions.assertEquals(null, board[i][j].getPiece());
                }
                if(i==1){
                    Assertions.assertEquals("p", board[i][j].getPiece().getType());
                }
                if(i==6){
                    Assertions.assertEquals("P", board[i][j].getPiece().getType());
                }

            }
        }
    }

    @Test
    void testBoardLoadFile(){
        board = BoardManager.loadBoardFromFile();
        Assertions.assertEquals("r", board[0][0].getPiece().getType());
        Assertions.assertEquals("n", board[0][1].getPiece().getType());
        Assertions.assertEquals("b", board[0][2].getPiece().getType());
        Assertions.assertEquals("q", board[0][3].getPiece().getType());
        Assertions.assertEquals("k", board[0][4].getPiece().getType());
        Assertions.assertEquals("b", board[0][5].getPiece().getType());
        Assertions.assertEquals("n", board[0][6].getPiece().getType());
        Assertions.assertEquals("r", board[0][7].getPiece().getType());
        Assertions.assertEquals("R", board[7][0].getPiece().getType());
        Assertions.assertEquals("N", board[7][1].getPiece().getType());
        Assertions.assertEquals("B", board[7][2].getPiece().getType());
        Assertions.assertEquals("Q", board[7][3].getPiece().getType());
        Assertions.assertEquals("K", board[7][4].getPiece().getType());
        Assertions.assertEquals("B", board[7][5].getPiece().getType());
        Assertions.assertEquals("N", board[7][6].getPiece().getType());
        Assertions.assertEquals("R", board[7][7].getPiece().getType());
        for(int i=1; i<7; i++){
            for(int j=0; j<8; j++){
                if(i!=1 && i!=6) {
                    Assertions.assertEquals(null, board[i][j].getPiece());
                }
                if(i==1){
                    Assertions.assertEquals("p", board[i][j].getPiece().getType());
                }
                if(i==6){
                    Assertions.assertEquals("P", board[i][j].getPiece().getType());
                }

            }
        }
    }

    @Test
    void testAddMoveVector(){
        MoveVector v1 = new MoveVector(1, 2, 1);
        MoveVector v2 = new MoveVector(1, 2, 2);
        MoveVector v3 = new MoveVector(1, 2, 3);
        MoveVector[] moves = new MoveVector[]{v1, v2};
        moves = MoveVector.addVector(moves, v3);
        Assertions.assertEquals(3, moves.length);
        Assertions.assertEquals(v1, moves[0]);
        Assertions.assertEquals(v2, moves[1]);
        Assertions.assertEquals(v3, moves[2]);
    }

    @Test
    void testMoveVectorContains(){
        MoveVector v1 = new MoveVector(1, 2, 1);
        MoveVector v2 = new MoveVector(2, 3, 2);
        MoveVector v3 = new MoveVector(3, 4, 3);
        MoveVector[] moves = new MoveVector[]{v1, v2, v3};
        Assertions.assertTrue(MoveVector.contains(moves, 1, 2));
        Assertions.assertTrue(MoveVector.contains(moves, 2, 3));
        Assertions.assertTrue(MoveVector.contains(moves, 3, 4));;
    }

    @Test
    void testMoveVectorMoveType() {
        MoveVector v1 = new MoveVector(1, 2, 1);
        MoveVector v2 = new MoveVector(2, 3, 2);
        MoveVector v3 = new MoveVector(3, 4, 3);
        MoveVector[] moves = new MoveVector[]{v1, v2, v3};
        Assertions.assertEquals(1, MoveVector.moveType(moves, 1, 2));
        Assertions.assertEquals(2, MoveVector.moveType(moves, 2, 3));
        Assertions.assertEquals(3, MoveVector.moveType(moves, 3, 4));
    }

    @Test
    void testPieceSetup(){
        Piece piece = new Bishop(new Vector(0,128), true);
        Assertions.assertTrue(piece.isWhite());
        Assertions.assertEquals(0, piece.getPoz().getX());
        Assertions.assertEquals(128, piece.getPoz().getY());
        Assertions.assertFalse(piece.hasMoved());
    }

    @Test
    void testQueenSetup(){
        Piece piece = new Queen(new Vector(0,128), false);
        Assertions.assertFalse(piece.isWhite());
        Assertions.assertEquals(9, piece.getValue());
        Assertions.assertEquals("q", piece.getType());
        Assertions.assertEquals("Queen", piece.toString());
    }

    @Test
    void testCopyVector() {
        Vector v1 = new Vector(0,128);
        Vector v2 = new Vector(128, 0);
        Vector v3 = new Vector(128, 128);
        Vector[] vs = new Vector[]{v1, v2, v3};
        Vector[] vs2 = Vector.copyVector(vs);
        Assertions.assertEquals(vs.length, vs2.length);
        for(int i = 0; i < vs.length; i++){
            Assertions.assertEquals(vs[i], vs2[i]);
        }
    }
}
