package View.Panels;

import Model.BoardManager;
import Model.BoardTile;
import Model.Vector.MoveVector;
import View.Buttons.BoardButton;

import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JPanel {
    private static BoardButton[][] boardButtons;

    public ChessBoard() {
        boardButtons = new BoardButton[8][8];
        setLayout(new GridLayout(8,8));
        setPreferredSize(new Dimension(800, 800));
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                boardButtons[i][j] = new BoardButton(i, j);
                boardButtons[i][j].setPreferredSize(new Dimension(100, 100));
                add(boardButtons[i][j]);
            }
        }
    }

    public void updateBoardButtons(){
        BoardTile[][] board = BoardManager.getBoard();
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if ((i+j)%2==0) boardButtons[i][j].setBackground(Color.WHITE);
                else boardButtons[i][j].setBackground(Color.BLACK);
                boardButtons[i][j].setCanMoveTo(false, 0, 0);
                if(board[i][j].getPiece()!=null){
                    boardButtons[i][j].setIcon(board[i][j].getPiece().getIcon());
                    boardButtons[i][j].setPoz(i, j);
                    boardButtons[i][j].setHasPiece(true);
                }
                else {
                    boardButtons[i][j].setIcon(null);
                    boardButtons[i][j].setHasPiece(false);
                }
            }
        }
    }

    public void updateBoardButtons(MoveVector[] moves, int x, int y){
        BoardTile[][] board = BoardManager.getBoard();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(MoveVector.contains(moves, i, j)) {
                    boardButtons[i][j].setCanMoveTo(true, x ,y);
                    if(boardButtons[i][j].getHasPiece()){
                        if(board[i][j].getPiece().isWhite()== board[x][y].getPiece().isWhite()){
                            boardButtons[i][j].setBackground(Color.GREEN);
                        }
                        else boardButtons[i][j].setBackground(Color.RED);
                    }
                    else {
                        if(MoveVector.moveType(moves, i, j) == 5 || MoveVector.moveType(moves, i, j) == 6){
                            boardButtons[i][j].setBackground(Color.RED);
                        }
                        else boardButtons[i][j].setBackground(Color.YELLOW);
                    }
                }
                else{
                    boardButtons[i][j].setCanMoveTo(false, 0, 0);
                    if ((i+j)%2==0) boardButtons[i][j].setBackground(Color.WHITE);
                    else boardButtons[i][j].setBackground(Color.BLACK);
                }
            }
        }
    }
}
