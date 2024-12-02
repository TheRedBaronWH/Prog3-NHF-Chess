package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Board.*;
import Vector.MoveVector;

public class UI extends JFrame {
    private static JPanel UI = new JPanel();

    private static JPanel BPieceContainer = new JPanel();
    private static JTextArea BtextArea = new JTextArea();

    private static JPanel WPieceContainer = new JPanel();
    private static JTextArea WtextArea = new JTextArea();

    private static JButton saveButton = new JButton("Save");

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

        BorderLayout UIlayout = new BorderLayout();
        UI.setLayout(UIlayout);
        //UI.setPreferredSize(new Dimension(400, 800));

        BtextArea.setPreferredSize(new Dimension(375, 200));
        BtextArea.setEditable(false);
        BPieceContainer.add(BtextArea);
        UI.add(BPieceContainer, BorderLayout.NORTH);

        WtextArea.setPreferredSize(new Dimension(375, 200));
        WtextArea.setEditable(false);
        WPieceContainer.add(WtextArea);
        UI.add(WPieceContainer, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Board.saveBoardCode(board);
            }
        });
        UI.add(saveButton, BorderLayout.EAST);

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
