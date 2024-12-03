package UI;

import Vector.*;
import Pieces.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PieceSelector extends JDialog {
    String[] pieces = {"Queen", "Rook", "Bishop", "Knight"};
    JComboBox cb = new JComboBox<>(pieces);
    JButton select = new JButton("Select");
    Piece piece;
    boolean isWhite;
    Vector poz;

    public Piece getPiece() { return piece; }

    class SelectorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String newPiece = (String) cb.getSelectedItem();
            if(isWhite) {
                if (newPiece.equals("Queen")) {
                    System.out.println("in");
                    piece = new Queen(poz, true);
                }
                if (newPiece.equals("Bishop")) {
                    piece = new Bishop(poz, true);
                }
                if (newPiece.equals("Knight")) {
                    piece = new Knight(poz, true);
                }
                if (newPiece.equals("Rook")) {
                    piece = new Rook(poz, true);
                }
            }
            else {
                if (newPiece.equals("Queen")) {
                    piece = new Queen(poz, false);
                }
                if (newPiece.equals("Bishop")) {
                    piece = new Bishop(poz, false);
                }
                if (newPiece.equals("Knight")) {
                    piece = new Knight(poz, false);
                }
                if (newPiece.equals("Rook")) {
                    piece = new Rook(poz, false);
                }
            }
            setVisible(false);
        }
    }

    public PieceSelector(boolean isWhite, Vector poz){
        this.isWhite = isWhite;
        this.poz = poz;
        setTitle("Piece Selector");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,200);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new FlowLayout());
        select.addActionListener(new SelectorListener());
        add(cb);
        add(select);
    }
}
