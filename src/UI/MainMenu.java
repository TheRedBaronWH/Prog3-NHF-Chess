package UI;

import Board.*;
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu(Board[][] board){
        UI ui = new UI(board);
        Rules rules = new Rules();

        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,800);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ImageIcon logo = new ImageIcon("src/Icons/BKing.png");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setSize(new Dimension(350,350));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(50,10,50,10));

        JPanel Buttons = new JPanel();
        Buttons.setLayout(new GridLayout(5, 1, 30, 30));
        Buttons.setBorder(BorderFactory.createEmptyBorder(10,400,10,400));

        JButton NewGameButton = new JButton("New Game");
        NewGameButton.setBackground(Color.GREEN);
        NewGameButton.addActionListener(e -> {
            ui.setVisible(true);
            dispose();
        });
        Buttons.add(NewGameButton);

        JButton LoadGameFileButton = new JButton("Load Game from file");
        LoadGameFileButton.setBackground(Color.GREEN);
        LoadGameFileButton.addActionListener(e -> {
            Board.loadBoardFile(board);
            UI.UpdateUI(board);
            ui.setVisible(true);
            dispose();
        });
        Buttons.add(LoadGameFileButton);

        JButton LoadGameCodeButton = new JButton("Load Game from code");
        LoadGameCodeButton.setBackground(Color.GREEN);
        LoadGameCodeButton.addActionListener(e -> {
            //Board.loadBoardCode(board, code);
            UI.UpdateUI(board);
            ui.setVisible(true);
            dispose();
        });
        Buttons.add(LoadGameCodeButton);

        JButton RulesButton = new JButton("Rules");
        RulesButton.setBackground(Color.YELLOW);
        RulesButton.addActionListener(e -> {
            rules.setVisible(true);
        });
        Buttons.add(RulesButton);

        JButton ExitButton = new JButton("Exit");
        ExitButton.setBackground(Color.RED);
        ExitButton.addActionListener(e -> {
            System.exit(0);
        });
        Buttons.add(ExitButton);

        panel.add(logoLabel, BorderLayout.NORTH);
        panel.add(Buttons, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
    }
}
