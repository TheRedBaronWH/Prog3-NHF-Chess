package View.Dialogs;

import Model.Pieces.*;
import Model.Vector.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PieceSelectorDialog extends JDialog {
    String[] pieces = {"Queen", "Rook", "Bishop", "Knight"};
    JComboBox<String> cb = new JComboBox<>(pieces);
    JButton select = new JButton("Select");
    Piece piece;
    boolean isWhite;
    Vector poz;

    public Piece getPiece() { return piece; }

    class SelectorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String newPiece = (String) cb.getSelectedItem();
            if(isWhite) {
                switch(newPiece) {
                    case "Queen":
                        piece = new Queen(poz, true);
                        break;
                    case "Bishop":
                        piece = new Bishop(poz, true);
                        break;
                    case "Knight":
                        piece = new Knight(poz, true);
                        break;
                    case "Rook":
                        piece = new Rook(poz, true);
                        break;
                    case null:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + newPiece);
                }
            }
            else {
                switch(newPiece) {
                    case "Queen":
                        piece = new Queen(poz, false);
                        break;
                    case "Bishop":
                        piece = new Bishop(poz, false);
                        break;
                    case "Knight":
                        piece = new Knight(poz, false);
                        break;
                    case "Rook":
                        piece = new Rook(poz, false);
                        break;
                    case null:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + newPiece);
                }
            }
            setVisible(false);
        }
    }

    public PieceSelectorDialog(boolean isWhite, Vector poz){
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
