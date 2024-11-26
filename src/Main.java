import Board.*;
import Vector.*;
import Pieces.*;

import java.util.Scanner;

public class Main {
    private static Board[][] board = new Board[8][8];

    public static void main(String[] args) {
        Board.setBoard(board);
        //String code = "p1p1p1p1/1p1p1p1p/8/8/p1p1p1p1/1p1p1p1p/8/8";
        //Board.loadBoardCode(board,code);
        Board.printBoard(board);

        Scanner scanner = new Scanner(System.in);
        MoveVector[] moves = new MoveVector[0];

        while(true){
            String line = scanner.nextLine();
            String[] cmd = line.split(" ");
            int i = Integer.parseInt(cmd[0]);
            int j = Integer.parseInt(cmd[1]);
            //moves = board[x][y].getPiece().availableMoves(board);
            //System.out.println(board[x][y].getPiece().getType() + " " + moves.length);
            //Board.printMoves(moves);
            line = scanner.nextLine();
            cmd = line.split(" ");
            int x = Integer.parseInt(cmd[0]);
            int y = Integer.parseInt(cmd[1]);
            System.out.println(Board.movePiece(board, board[i][j].getPiece(), x, y));
            Board.printBoard(board);
        }
    }
}
