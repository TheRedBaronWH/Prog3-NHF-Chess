package UI;

import Board.*;
import Pieces.*;
import javax.swing.*;
import java.awt.*;

public class PieceSelector extends JFrame {
    public PieceSelector(Piece piece){
        Object[] pieces = {"Queen", "Rook", "Bishop", "Knight"};

        setTitle("Piece Selector");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,200);
        setLayout(new FlowLayout());
        JComboBox cb = new JComboBox<>(pieces);
        JButton select = new JButton("Select");
        select.addActionListener(e -> {
            Board.switchPiece(piece, (String) cb.getSelectedItem());
            System.out.println((String) cb.getSelectedItem());
        });
        add(cb);
        add(select);
    }
}
