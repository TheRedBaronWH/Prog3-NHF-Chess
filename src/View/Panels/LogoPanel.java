package View.Panels;

import javax.swing.*;
import java.awt.*;

public class LogoPanel extends JPanel {
    ImageIcon logo = new ImageIcon("src/Resources/BKing.png");
    JLabel logoLabel = new JLabel(logo, SwingConstants.CENTER);
    JLabel titleLabel = new JLabel("ChessGame", SwingConstants.CENTER);

    public LogoPanel() {
        setLayout(new BorderLayout());

        logoLabel.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10));
        add(logoLabel, BorderLayout.CENTER);

        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        add(titleLabel, BorderLayout.SOUTH);
    }
}
