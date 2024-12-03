package UI;

import javax.swing.*;
import java.awt.*;

import Board.*;
import Pieces.*;
import Vector.MoveVector;

public class UI extends JFrame {
    private static JPanel UI = new JPanel();

    private static JPanel BlackContainer = new JPanel();
    private static ForfeitButton BlackForfeit = new ForfeitButton("Forfeit", false);
    private static JTextArea BtextArea = new JTextArea();
    private static JTextArea BScore = new JTextArea("0");

    private static JPanel WhiteContainer = new JPanel();
    private static ForfeitButton WhiteForfeit = new ForfeitButton("Forfeit", true);
    private static JTextArea WtextArea = new JTextArea();
    private static JTextArea WScore = new JTextArea("0");

    private static JPanel MiddleContainer = new JPanel();
    private static SaveButton saveButton = new SaveButton("Save");

    private static JPanel chessBoard = new JPanel();
    private static Button[][] buttons = new Button[8][8];

    private static boolean whiteTurn = true;
    public static boolean getWhiteTurn() { return whiteTurn; }

    private static Board[][] board;
    public static Board[][] getBoard(){ return board; }

    public UI(Board[][] board) {
        this.board = board;
        setTitle("ChessGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        BorderLayout UIlayout = new BorderLayout();
        UI.setLayout(UIlayout);
        //UI.setPreferredSize(new Dimension(400, 800));

        BScore.setEditable(false);
        BScore.setPreferredSize(new Dimension(375, 20));
        BtextArea.setPreferredSize(new Dimension(375, 180));
        BtextArea.setEditable(false);
        BlackContainer.setLayout(new BorderLayout());
        BlackContainer.add(BScore, BorderLayout.NORTH);
        BlackContainer.add(BtextArea, BorderLayout.CENTER);
        BlackForfeit.setBackground(Color.RED);
        BlackContainer.add(BlackForfeit, BorderLayout.SOUTH);
        UI.add(BlackContainer, BorderLayout.NORTH);

        WScore.setEditable(false);
        WScore.setPreferredSize(new Dimension(375, 20));
        WtextArea.setPreferredSize(new Dimension(375, 180));
        WtextArea.setEditable(false);
        WhiteContainer.setLayout(new BorderLayout());
        WhiteContainer.add(WScore, BorderLayout.SOUTH);
        WhiteContainer.add(WtextArea, BorderLayout.CENTER);
        WhiteForfeit.setBackground(Color.RED);
        WhiteContainer.add(WhiteForfeit, BorderLayout.NORTH);
        UI.add(WhiteContainer, BorderLayout.SOUTH);

        MiddleContainer.setLayout(new BorderLayout());
        saveButton.addActionListener(e -> Board.saveBoardCode(board));
        saveButton.setBackground(Color.GREEN);
        MiddleContainer.add(saveButton, BorderLayout.CENTER);
        UI.add(MiddleContainer, BorderLayout.CENTER);

        GridLayout layout = new GridLayout(8,8);
        chessBoard.setLayout(layout);
        chessBoard.setPreferredSize(new Dimension(800, 800));
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                buttons[i][j] = new Button(i, j);
                if(board[i][j].getPiece()!=null) {
                    buttons[i][j].setIcon(board[i][j].getPiece().getIcon());
                    buttons[i][j].setHasPiece(true);
                }
                if ((i+j)%2==0) buttons[i][j].setBackground(Color.WHITE);
                else buttons[i][j].setBackground(Color.BLACK);
                //buttons[i][j].setOpaque(true);
                buttons[i][j].setPreferredSize(new Dimension(100, 100));
                chessBoard.add(buttons[i][j]);
            }
        }
        add(chessBoard, BorderLayout.WEST);
        add(UI, BorderLayout.EAST);
    }

    public static void WUpdatePieces(Piece piece){
        WScore.setText(String.valueOf(Integer.parseInt(WScore.getText())+piece.getValue()));
        WtextArea.setText(piece.toString()+ " +" + piece.getValue() + "\n"+WtextArea.getText());
    }

    public static void BUpdatePieces(Piece piece){
        BScore.setText(String.valueOf(Integer.parseInt(BScore.getText())+piece.getValue()));
        BtextArea.setText(piece.toString()+ " +" + piece.getValue() + "\n"+BtextArea.getText());
    }

    public static void UpdateUI(Board[][] board){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if ((i+j)%2==0) buttons[i][j].setBackground(Color.WHITE);
                else buttons[i][j].setBackground(Color.BLACK);
                buttons[i][j].setCanMoveTo(false, 0, 0);
                if(board[i][j].getPiece()!=null){
                    buttons[i][j].setIcon(board[i][j].getPiece().getIcon());
                    buttons[i][j].setPoz(i, j);
                    buttons[i][j].setHasPiece(true);
                }
                else {
                    buttons[i][j].setIcon(null);
                    buttons[i][j].setHasPiece(false);
                }
            }
        }
    }

    public static void UpdateUI(MoveVector[] moves, int x, int y){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(MoveVector.contains(moves, i, j)) {
                    buttons[i][j].setCanMoveTo(true, x ,y);
                    if(buttons[i][j].getHasPiece()){
                        if(board[i][j].getPiece().isWhite()==board[x][y].getPiece().isWhite()){
                            buttons[i][j].setBackground(Color.GREEN);
                        }
                        else buttons[i][j].setBackground(Color.RED);
                    }
                    else {
                        if(MoveVector.moveType(moves, i, j) == 5 || MoveVector.moveType(moves, i, j) == 6){
                            buttons[i][j].setBackground(Color.RED);
                        }
                        else buttons[i][j].setBackground(Color.YELLOW);
                    }
                }
                else{
                    buttons[i][j].setCanMoveTo(false, 0, 0);
                    if ((i+j)%2==0) buttons[i][j].setBackground(Color.WHITE);
                    else buttons[i][j].setBackground(Color.BLACK);
                }
            }
        }
    }

}
