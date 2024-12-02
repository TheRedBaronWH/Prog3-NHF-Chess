import javax.swing.*;
import java.awt.*;
import Board.*;

public class UI extends JFrame {
    private JPanel UI = new JPanel();
    private JButton[][] buttons = new JButton[8][8];

    UI(Board[][] board) {
        setTitle("ChessGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        GridLayout layout = new GridLayout(8, 8);
        UI.setLayout(layout);
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                buttons[i][j] = new JButton();
                if(board[i][j].getPiece()!=null) buttons[i][j].setIcon(board[i][j].getPiece().getIcon());
                if ((i+j)%2==0) buttons[i][j].setBackground(Color.WHITE);
                else buttons[i][j].setBackground(Color.BLACK);
                buttons[i][j].setOpaque(true);
                UI.add(buttons[i][j]);
            }
        }
        add(UI);
    }

    public void UpdateUI(Board[][] board){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(board[i][j].getPiece()!=null) buttons[i][j].setIcon(board[i][j].getPiece().getIcon());
                else buttons[i][j].setIcon(null);
            }
        }
    }

}
