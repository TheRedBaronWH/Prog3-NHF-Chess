import Board.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board[][] board = new Board[8][8];

    @Test
    void testBoardInit() {
        Board.setBoard(board);
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
        Board.setBoard(board);
        Assertions.assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", Board.saveBoardCode(board));
    }

    @Test
    void testBoardLoadCode(){
        Board.loadBoardCode(board, "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
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
}
