package View.Panels;

import View.Buttons.*;
import View.Listeners.ActionListener;
import View.Listeners.ActionListenerStringInput;
import View.Screens.RulesScreen;

import javax.swing.*;
import java.awt.*;

public class MainMenuButtonsPanel extends JPanel {
    RulesScreen rulesScreen = new RulesScreen();
    public MainMenuButtonsPanel(
            ActionListener newGameActionListener,
            ActionListener loadFromFileActionListener,
            ActionListenerStringInput loadFromCodeActionListener,
            ActionListener rulesActionListener
    ) {
        setLayout(new GridLayout(5, 1, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(10,400,10,400));

        NewGameButton newGameButton = new NewGameButton(newGameActionListener);
        add(newGameButton);

        LoadFromFileButton loadFromFileButton = new LoadFromFileButton(loadFromFileActionListener);
        add(loadFromFileButton);

        LoadFromCodePanel loadFromCodePanel = new LoadFromCodePanel(loadFromCodeActionListener);
        add(loadFromCodePanel);

        RulesButton rulesButton = new RulesButton(rulesActionListener);
        add(rulesButton);

        ExitButton exitButton = new ExitButton();
        add(exitButton);
    }
}
