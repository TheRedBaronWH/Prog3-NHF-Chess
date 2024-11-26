import Board.*;
import Vector.*;
import Pieces.*;

import java.util.Scanner;

public class Main {
    private static Board[][] board = new Board[8][8];
    private static boolean KingDanger = false;
    private static boolean gameEnd = false;
    private static int winner;

    public static void setGameEnd(int i){
        gameEnd = true;
        winner = i;
    }

    public static void main(String[] args) {
        Board.setBoard(board);
        //String code = "p1p1p1p1/1p1p1p1p/8/8/p1p1p1p1/1p1p1p1p/8/8";
        //Board.loadBoardCode(board,code);
        Board.printBoard(board);

        Scanner scanner = new Scanner(System.in);
        MoveVector[] moves = new MoveVector[0];

        while(!gameEnd){
            String line = scanner.nextLine();
            String[] cmd = line.split(" ");
            switch(cmd[0]) {
                case "HasPiece" -> {
                    int i = Integer.parseInt(cmd[1]);
                    int j = Integer.parseInt(cmd[2]);
                    if(board[i][j] == null){
                        System.out.println("null");
                    }
                    else System.out.println(board[i][j].getPiece().getType());
                }

                case "AvailableMoves" -> {
                    int i = Integer.parseInt(cmd[1]);
                    int j = Integer.parseInt(cmd[2]);
                    moves = board[i][j].getPiece().availableMoves(board);
                    System.out.println(board[i][j].getPiece().getType() + " " + moves.length);
                    Board.printMoves(moves);
                }

                case "Move" -> {
                    int i = Integer.parseInt(cmd[1]);
                    int j = Integer.parseInt(cmd[2]);
                    line = scanner.nextLine();
                    cmd = line.split(" ");
                    int x = Integer.parseInt(cmd[0]);
                    int y = Integer.parseInt(cmd[1]);
                    int move = Board.movePiece(board, board[i][j].getPiece(), x, y);
                    if(move == 2 || move == 3){
                        setGameEnd(move);
                    }
                    System.out.println(move);
                    Board.printBoard(board);
                }

                case "CheckHasMoved" -> {
                    int x = Integer.parseInt(cmd[1]);
                    int y = Integer.parseInt(cmd[2]);
                    System.out.println(board[x][y].getPiece().hasMoved());
                }
            }
        }
        if(winner == 2) System.out.println("Congratulations, black won!");
        else System.out.println("Congratulations, white won!");
    }
}
