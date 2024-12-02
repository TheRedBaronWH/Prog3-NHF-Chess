import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    /*
    private JPanel[][] UI = new JPanel[8][8];

    UI() {
        setTitle("ChessGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,800);
        GridLayout layout = new GridLayout(8, 8);
        setLayout(layout);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                UI[i][j] = new JPanel();
                UI[i][j].add(new JButton());
            }
        }
        for(int i = 0 ; i < 8; i++) {
            for(int j = 0 ; j < 8; j++) {
                add(UI[i][j]);
            }
        }
    }
    */

    private JPanel UI = new JPanel();
    private JButton[][] buttons = new JButton[8][8];

    UI() {
        setTitle("ChessGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        GridLayout layout = new GridLayout(8, 8);
        UI.setLayout(layout);
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                buttons[i][j] = new JButton();
                if ((i+j)%2==0) buttons[i][j].setBackground(Color.WHITE);
                else buttons[i][j].setBackground(Color.BLACK);
                UI.add(buttons[i][j]);
            }
        }
        add(UI);
    }

}
