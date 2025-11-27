package View.Screens;

import Model.Pieces.Piece;
import Model.Vector.MoveVector;
import View.Buttons.SaveGameButton;
import View.Panels.BlackPlayerPanel;
import View.Panels.ChessBoard;
import View.Panels.WhitePlayerPanel;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JFrame {
    private static JPanel UI = new JPanel();

    private static BlackPlayerPanel blackPlayerPanel = new BlackPlayerPanel();

    private static WhitePlayerPanel whitePlayerPanel= new WhitePlayerPanel();

    private static JPanel saveButtonPanel = new JPanel();
    private static SaveGameButton saveButton = new SaveGameButton();

    private static ChessBoard chessBoard = new ChessBoard();

    public GameScreen() {
        setTitle("ChessGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        UI.setLayout(new BorderLayout());

        add(chessBoard, BorderLayout.WEST);

        UI.add(blackPlayerPanel, BorderLayout.NORTH);

        UI.add(whitePlayerPanel, BorderLayout.SOUTH);

        saveButtonPanel.setLayout(new BorderLayout());
        saveButtonPanel.add(saveButton, BorderLayout.CENTER);
        UI.add(saveButtonPanel, BorderLayout.CENTER);

        add(UI, BorderLayout.EAST);
    }

    public void StartGame() {
        UpdateUI();
        setVisible(true);
    }

    public static void UpdateWhitePieceCounter(Piece piece){
        whitePlayerPanel.UpdatePieceCounter(piece);
    }

    public static void UpdateBlackPieceCounter(Piece piece){
        blackPlayerPanel.UpdatePieceCounter(piece);
    }

    public static void UpdateUI(){
        chessBoard.updateBoard();
    }

    public static void UpdateUI_HighlightPossibleMoves(MoveVector[] moves, int x, int y){
        chessBoard.updateBoard_HighlightPossibleMoves(moves, x, y);
    }

}
