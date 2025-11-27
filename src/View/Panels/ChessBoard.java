package View.Panels;

import Model.BoardManager;
import Model.BoardTile;
import Model.Vector.MoveVector;
import View.Buttons.BoardTileButton;

import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JPanel {
    private static BoardTileButton[][] boardTileButtons;

    public ChessBoard() {
        boardTileButtons = new BoardTileButton[8][8];
        setLayout(new GridLayout(8,8));
        setPreferredSize(new Dimension(800, 800));
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                boardTileButtons[i][j] = new BoardTileButton(i, j);
                boardTileButtons[i][j].setPreferredSize(new Dimension(100, 100));
                add(boardTileButtons[i][j]);
            }
        }
    }

    public void updateBoard(){
        BoardTile[][] board = BoardManager.getBoard();
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if ((i+j)%2==0) boardTileButtons[i][j].setBackground(Color.WHITE);
                else boardTileButtons[i][j].setBackground(Color.BLACK);
                boardTileButtons[i][j].notMovingHere();
                if(board[i][j].getPiece()!=null){
                    boardTileButtons[i][j].setIcon(board[i][j].getPiece().getIcon());
                    boardTileButtons[i][j].setPoz(i, j);
                    boardTileButtons[i][j].setHasPiece(true);
                }
                else {
                    boardTileButtons[i][j].setIcon(null);
                    boardTileButtons[i][j].setHasPiece(false);
                }
            }
        }
    }

    public void updateBoard_HighlightPossibleMoves(MoveVector[] moves, int x, int y){
        BoardTile[][] board = BoardManager.getBoard();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(MoveVector.contains(moves, i, j)) {
                    boardTileButtons[i][j].movingHereFrom(x, y);
                    if(boardTileButtons[i][j].getHasPiece()){
                        if(board[i][j].getPiece().isWhite()== board[x][y].getPiece().isWhite()){
                            boardTileButtons[i][j].setBackground(Color.GREEN);
                        }
                        else boardTileButtons[i][j].setBackground(Color.RED);
                    }
                    else {
                        if(MoveVector.moveType(moves, i, j) == 5 || MoveVector.moveType(moves, i, j) == 6){
                            boardTileButtons[i][j].setBackground(Color.RED);
                        }
                        else boardTileButtons[i][j].setBackground(Color.YELLOW);
                    }
                }
                else{
                    boardTileButtons[i][j].notMovingHere();
                    if ((i+j)%2==0) boardTileButtons[i][j].setBackground(Color.WHITE);
                    else boardTileButtons[i][j].setBackground(Color.BLACK);
                }
            }
        }
    }
}
