package View.Screens;

import Model.BoardManager;
import View.Panels.ButtonsPanel;
import View.Panels.LogoPanel;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    GameScreen gameScreen = new GameScreen();
    RulesScreen rulesScreen = new RulesScreen();

    public MainMenu(){
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,800);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BorderLayout());

        LogoPanel logoPanel = new LogoPanel();
        ButtonsPanel buttonsPanel = new ButtonsPanel(
                ()->{
                    BoardManager.createBasicBoard();
                    gameScreen.StartGame();
                    dispose();
                },
                ()->{
                    BoardManager.loadBoardFromFile();
                    gameScreen.StartGame();
                    dispose();
                },
                (String code)->{
                    BoardManager.loadBoardFromCode(code);
                    gameScreen.StartGame();
                    dispose();
                },
                ()->{
                    rulesScreen.setVisible(true);
                }
        );

        mainMenuPanel.add(logoPanel, BorderLayout.NORTH);
        mainMenuPanel.add(buttonsPanel, BorderLayout.CENTER);
        add(mainMenuPanel, BorderLayout.CENTER);
    }
}
