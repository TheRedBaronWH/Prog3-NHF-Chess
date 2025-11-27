package Model;

import Model.Pieces.King;
import Model.Pieces.Piece;
import Model.Pieces.Rook;
import Model.Vector.MoveVector;
import Model.Vector.Vector;
import View.Dialogs.GameEndDialog;
import View.Dialogs.PieceSelectorDialog;
import View.Screens.GameScreen;

public class BoardTile {
    protected Vector poz;
    protected Piece piece;

    public BoardTile(Vector poz) {
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

    public static Piece checkPawnReachedEnd(Piece piece, int x, int y) {
        if((piece.getType().equals("p") && x==7) || (piece.getType().equals("P") && x==0)){
            PieceSelectorDialog selector = new PieceSelectorDialog(piece.isWhite(), piece.getPoz());
            selector.setVisible(true);
            piece = selector.getPiece();
            selector.dispose();
            GameScreen.UpdateUI();
        }
        return piece;
    }

    public static void movePiece(Piece piece, int x, int y) {
        BoardTile[][] board = BoardManager.getBoard();
        int i = 0;
        boolean sanc = false;
        if(piece.isWhite() == BoardManager.getWhiteTurn()) {
            MoveVector[] moves = piece.getPossibleMoves();
            if (MoveVector.contains(moves, x, y)) {
                i = 1;
                piece.setHasMoved();
                switch (MoveVector.moveType(moves, x, y)) {
                    case MoveTypes.PAWN_SWITCH -> {
                        PieceSelectorDialog selector = new PieceSelectorDialog(piece.isWhite(), piece.getPoz());
                        selector.setVisible(true);
                        piece = selector.getPiece();
                        selector.dispose();
                        GameScreen.UpdateUI();
                    }

                    case MoveTypes.PAWN_ENPASSANT_R -> {
                        if (board[x][y - 1].getPiece().isWhite()) i = 2;
                        else i = 3;
                        if (i == 2) GameScreen.UpdateBlackPieceCounter(board[x][y - 1].getPiece());
                        else GameScreen.UpdateWhitePieceCounter(board[x][y - 1].getPiece());
                        board[x][y - 1].setPiece(null);
                    }

                    case MoveTypes.PAWN_ENPASSANT_L -> {
                        if (board[x][y + 1].getPiece().isWhite()) i = 2;
                        else i = 3;
                        if (i == 2) GameScreen.UpdateBlackPieceCounter(board[x][y + 1].getPiece());
                        else GameScreen.UpdateWhitePieceCounter(board[x][y + 1].getPiece());
                        board[x][y + 1].setPiece(null);
                    }

                    case MoveTypes.KING_ROOK_SWITCH -> {
                        sanc = true;
                    }

                    case MoveTypes.KING_TAKEN -> {
                        String winner;
                        if (board[x][y].getPiece().getType().equals("K")) {
                            winner = "Black";
                        }
                        else {
                            winner = "White";
                        }
                        GameEndDialog endScreen = new GameEndDialog(winner);
                        endScreen.setVisible(true);
                        System.exit(0);
                    }

                    case MoveTypes.PIECE_TAKEN -> {
                        if (board[x][y].getPiece().isWhite()) i = 2;
                        else i = 3;
                        piece = checkPawnReachedEnd(piece, x, y);
                        if (i == 2) GameScreen.UpdateBlackPieceCounter(board[x][y].getPiece());
                        else GameScreen.UpdateWhitePieceCounter(board[x][y].getPiece());
                    }
                }
                Vector piecePoz = piece.getPoz();
                board[piecePoz.getX()][piecePoz.getY()].setPiece(null);
                if(sanc) {
                    if(piece.toString().equals("King")){
                        if(y==0) {
                            piece.setPoz(new Vector(x, y+2));
                            board[x][y+2].setPiece(piece);
                            Piece piece1 = new Rook(new Vector(x, y+3), piece.isWhite());
                            board[x][y+3].setPiece(piece1);
                        }
                        else {
                            piece.setPoz(new Vector(x, y-1));
                            board[x][y-1].setPiece(piece);
                            Piece piece1 = new Rook(new Vector(x, y-2), piece.isWhite());
                            board[x][y-2].setPiece(piece1);
                        }
                    }
                    else{
                        if(piece.getPoz().getY()==0) {
                            piece.setPoz(new Vector(x, y-1));
                            board[x][y-1].setPiece(piece);
                            Piece piece1 = new King(new Vector(x, y-2), piece.isWhite());
                            board[x][y-2].setPiece(piece1);
                        } else {
                            piece.setPoz(new Vector(x, y+1));
                            board[x][y+1].setPiece(piece);
                            Piece piece1 = new King(new Vector(x, y+2), piece.isWhite());
                            board[x][y+2].setPiece(piece1);
                        }
                    }
                    board[x][y].setPiece(null);
                    sanc = false;
                }
                else {
                    piece.setPoz(new Vector(x, y));
                    board[x][y].setPiece(piece);
                }

                //System.out.println(MoveVector.moveType(moves, x, y));
                GameScreen.UpdateUI();
                BoardManager.changeTurn();
            }
        }
    }

    public static void printBoard(BoardTile[][] boardTile){
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardTile[i][j].getPiece() != null) {
                    System.out.print(boardTile[i][j].getPiece().getType() + " ");
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
}
