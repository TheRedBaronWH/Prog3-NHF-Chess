package View.Screens;

import Model.BoardManager;
import View.Panels.MainMenuButtonsPanel;
import View.Panels.MainMenuTitlePanel;

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

        MainMenuTitlePanel titlePanel = new MainMenuTitlePanel();
        MainMenuButtonsPanel buttonsPanel = new MainMenuButtonsPanel(
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

        mainMenuPanel.add(titlePanel, BorderLayout.NORTH);
        mainMenuPanel.add(buttonsPanel, BorderLayout.CENTER);
        add(mainMenuPanel, BorderLayout.CENTER);
    }
}
