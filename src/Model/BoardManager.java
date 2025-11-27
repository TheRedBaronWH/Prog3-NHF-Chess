package Model;

import Model.Pieces.*;
import Model.Vector.Vector;

import java.io.*;
import java.util.Set;

public class BoardManager {
    private static final Set<String> PIECE_TYPES = Set.of("r", "R", "b", "B", "n", "N", "k", "K", "q", "Q", "p", "P");
    private static BoardTile[][] board = null;
    private static boolean whiteTurn = true;

    public static BoardTile[][] getBoard() {
        if(board == null) createBasicBoard();
        return board;
    }
    public static void setBoard(BoardTile[][] board) {
        BoardManager.board = board;
    }
    protected static void resetBoard() {
        board = null;
    }

    public static String getBoardCode(){
        String code = "";
        int emptyTileCount;
        for(int i=0; i<8; i++) {
            emptyTileCount = 0;
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() != null) {
                    if (emptyTileCount != 0) {
                        code = code.concat(String.valueOf(emptyTileCount));
                        emptyTileCount = 0;
                    }
                    code = code.concat(board[i][j].getPiece().getType());
                } else {
                    emptyTileCount++;
                }
            }
            if (emptyTileCount != 0) code = code.concat(String.valueOf(emptyTileCount));
            if (i != 7) code = code.concat("/");
        }
        return code;
    }

    public static void saveBoard() {
        File fout = new File("BoardCode.txt");
        try {
            if (!fout.exists()) {
                if (fout.createNewFile()) {
                    FileWriter fw = new FileWriter(fout);
                    fw.write(getBoardCode());
                    fw.close();
                }
                else {
                    throw new RuntimeException("Failed to create file");
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static BoardTile[][] createBasicBoard() {
        setBoard(new BoardTile[8][8]);
        for(int i = 0; i < 8; i++){
            board[0][i] = new BoardTile(new Vector(0, i));
            board[7][i] = new BoardTile(new Vector(7, i));
        }
        board[0][0].setPiece(new Rook(board[0][0].getPoz(), false));
        board[0][1].setPiece(new Knight(board[0][1].getPoz(), false));
        board[0][2].setPiece(new Bishop(board[0][2].getPoz(), false));
        board[0][3].setPiece(new Queen(board[0][3].getPoz(), false));
        board[0][4].setPiece(new King(board[0][4].getPoz(), false));
        board[0][5].setPiece(new Bishop(board[0][5].getPoz(), false));
        board[0][6].setPiece(new Knight(board[0][6].getPoz(), false));
        board[0][7].setPiece(new Rook(board[0][7].getPoz(), false));
        board[7][0].setPiece(new Rook(board[7][0].getPoz(), true));
        board[7][1].setPiece(new Knight(board[7][1].getPoz(), true));
        board[7][2].setPiece(new Bishop(board[7][2].getPoz(), true));
        board[7][3].setPiece(new Queen(board[7][3].getPoz(), true));
        board[7][4].setPiece(new King(board[7][4].getPoz(), true));
        board[7][5].setPiece(new Bishop(board[7][5].getPoz(), true));
        board[7][6].setPiece(new Knight(board[7][6].getPoz(), true));
        board[7][7].setPiece(new Rook(board[7][7].getPoz(), true));
        for(int i = 1; i < 7; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = new BoardTile(new Vector(i, j));
                if(i==1) {
                    board[i][j].setPiece(new Pawn(board[i][j].getPoz(), false));
                }
                if(i==6) {
                    board[i][j].setPiece(new Pawn(board[i][j].getPoz(), true));
                }
            }
        }
        return board;
    }

    public static BoardTile[][] loadBoardFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("BoardCode.txt"));
            String code = br.readLine();
            return loadBoardFromCode(code);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BoardTile[][] loadBoardFromCode(String c) {
        String[] code = c.split("");
        if (!checkCodeValidity(code)) {
            return null;
        }
        setBoard(new BoardTile[8][8]);
        int skip = 0;
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new BoardTile(new Vector(i, j));
                if (skip == 0) {
                    if (code[count].equals("/")) count++;
                    //System.out.println(code[count] + " " + i + " " + j);
                    switch (code[count]) {
                        case "r" -> board[i][j].setPiece(new Rook(board[i][j].getPoz(), false));
                        case "R" -> board[i][j].setPiece(new Rook(board[i][j].getPoz(), true));
                        case "n" -> board[i][j].setPiece(new Knight(board[i][j].getPoz(), false));
                        case "N" -> board[i][j].setPiece(new Knight(board[i][j].getPoz(), true));
                        case "b" -> board[i][j].setPiece(new Bishop(board[i][j].getPoz(), false));
                        case "B" -> board[i][j].setPiece(new Bishop(board[i][j].getPoz(), true));
                        case "q" -> board[i][j].setPiece(new Queen(board[i][j].getPoz(), false));
                        case "Q" -> board[i][j].setPiece(new Queen(board[i][j].getPoz(), true));
                        case "k" -> board[i][j].setPiece(new King(board[i][j].getPoz(), false));
                        case "K" -> board[i][j].setPiece(new King(board[i][j].getPoz(), true));
                        case "p" -> board[i][j].setPiece(new Pawn(board[i][j].getPoz(), false));
                        case "P" -> board[i][j].setPiece(new Pawn(board[i][j].getPoz(), true));
                        default -> {
                            skip = Integer.parseInt(code[count]) - 1;
                            board[i][j].setPiece(null);
                        }
                    }
                    count++;
                }
                else {
                    skip--;
                }
            }
        }
        return board;
    }

    public static boolean checkCodeValidity(String[] code){
        int n = 0;
        for(String piece: code){
            if(PIECE_TYPES.contains(piece)) n++;
            else if(Character.isDigit(piece.charAt(0))) n+=Character.getNumericValue(piece.charAt(0));
        }
        return n == 64;
    }

    public static boolean getWhiteTurn() { return whiteTurn; }

    public static void changeTurn() { whiteTurn = !whiteTurn; }
}
