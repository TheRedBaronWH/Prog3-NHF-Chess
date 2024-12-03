package Board;

import UI.*;
import Pieces.*;
import Vector.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class Board {
    private static final Set<String> STRINGS = Set.of("r", "R", "b", "B", "n", "N", "k", "K", "q", "Q", "p", "P");
    protected Vector poz;
    protected Piece piece;

    public Board(Vector poz) {
        this.poz = poz;
        piece = null;
    }

    public Vector getPoz() {
        return poz;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public static Piece checkPawnEnd(Board[][] board, Piece piece, int x, int y) {
        if((piece.getType().equals("p") && x==7) || (piece.getType().equals("P") && x==0)){
            PieceSelector selector = new PieceSelector(piece.isWhite(), piece.getPoz());
            selector.setVisible(true);
            piece = selector.getPiece();
            selector.dispose();
            UI.UpdateUI(board);
        }
        return piece;
    }

    public static int movePiece(Board[][] board, Piece piece, int x, int y) {
        int i = 0;
        if(piece.isWhite()==UI.getWhiteTurn()) {
            Piece temp = null;
            MoveVector[] moves = piece.availableMoves(board);
            if (MoveVector.contains(moves, x, y)) {
                i = 1;
                piece.setHasMoved();
                switch (MoveVector.moveType(moves, x, y)) {
                    //Pawn csere
                    case 7 -> {
                        PieceSelector selector = new PieceSelector(piece.isWhite(), piece.getPoz());
                        selector.setVisible(true);
                        piece = selector.getPiece();
                        selector.dispose();
                        UI.UpdateUI(board);
                    }
                    //EnPassant jobbra
                    case 6 -> {
                        if (board[x][y - 1].getPiece().isWhite()) i = 2;
                        else i = 3;
                        if (i == 2) UI.BUpdatePieces(board[x][y - 1].getPiece());
                        else UI.WUpdatePieces(board[x][y - 1].getPiece());
                        board[x][y - 1].setPiece(null);
                    }
                    //EnPassant balra
                    case 5 -> {
                        if (board[x][y + 1].getPiece().isWhite()) i = 2;
                        else i = 3;
                        if (i == 2) UI.BUpdatePieces(board[x][y + 1].getPiece());
                        else UI.WUpdatePieces(board[x][y + 1].getPiece());
                        board[x][y + 1].setPiece(null);
                    }
                    //sanc
                    case 4 -> {
                        temp = board[x][y].getPiece();
                        temp.setPoz(new Vector(piece.getPoz().getX(), piece.getPoz().getY()));
                        temp.setHasMoved();
                    }
                    //király leszedés
                    case 3 -> {
                        if (board[x][y].getPiece().getType().equals("K")) {
                            UI.endScreen("Black");
                        } else UI.endScreen("White");
                        UI.shutDown();
                    }
                    //normális leszedés
                    case 2 -> {
                        if (board[x][y].getPiece().isWhite()) i = 2;
                        else i = 3;
                        piece = checkPawnEnd(board, piece, x, y);
                        if (i == 2) UI.BUpdatePieces(board[x][y].getPiece());
                        else UI.WUpdatePieces(board[x][y].getPiece());
                    }
                }
                board[piece.getPoz().getX()][piece.getPoz().getY()].setPiece(temp);
                piece.setPoz(new Vector(x, y));
                board[x][y].setPiece(piece);
                //System.out.println(MoveVector.moveType(moves, x, y));
                UI.UpdateUI(board);
                UI.changeTurn();
                return i;
            }
        }
        return i;
    }

    public static void setBoard(Board[][] board){
        for(int i = 0; i < 8; i++){
            board[0][i] = new Board(new Vector(0, i));
            board[7][i] = new Board(new Vector(7, i));
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
                board[i][j] = new Board(new Vector(i, j));
                if(i==1) {
                    board[i][j].setPiece(new Pawn(board[i][j].getPoz(), false));
                }
                if(i==6) {
                    board[i][j].setPiece(new Pawn(board[i][j].getPoz(), true));
                }
            }
        }
    }

    public static Board[][] loadBoardFile(Board[][] board){
        try {
            BufferedReader br = new BufferedReader(new FileReader("BoardCode.txt"));
            String code = br.readLine();
            return loadBoardCode(board, code);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean goodCode(String[] code){
        int n = 0;
        for(int i=0; i<code.length; i++){
            if(STRINGS.contains(code[i])) n++;
            if(Character.isDigit(code[i].charAt(0))) n+=Character.getNumericValue(code[i].charAt(0));
        }
        if(n == 64) return true;
        else return false;
    }

    public static Board[][] loadBoardCode(Board[][] board, String c){
        int skip = 0;
        int count = 0;
        String[] code = c.split("");
        if(goodCode(code)) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    board[i][j] = new Board(new Vector(i, j));
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
                    } else {
                        skip--;
                    }
                }
            }
            return board;
        }
        else return null;
    }

    public static void printBoard(Board[][] board){
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() != null) {
                    System.out.print(board[i][j].getPiece().getType() + " ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    public static void printMoves(MoveVector[] moves){
        int[][] board = new int[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                board[i][j]=0;
            }
        }
        for (MoveVector move : moves) {
            board[move.getX()][move.getY()] = move.getTakeable();
        }
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String saveBoardCode(Board[][] board){
        String code = "";
        int len;
        for(int i=0; i<8; i++){
            len = 0;
            for(int j=0; j<8; j++){
                if(board[i][j].getPiece()!=null) {
                    if(len!=0) {
                        code = code.concat(String.valueOf(len));
                        len = 0;
                    }
                    code = code.concat(board[i][j].getPiece().getType());
                }
                else {
                    len++;
                }
            }
            if(len!=0) code = code.concat(String.valueOf(len));
            if(i!=7) code = code.concat("/");
        }
        //System.out.println(code);
        return code;
    }
}
