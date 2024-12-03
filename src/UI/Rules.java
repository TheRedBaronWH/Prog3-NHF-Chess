package UI;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class Rules extends JFrame {
    public Rules() {
        String text = "Fehér kezd;\n" +
                "Bábuk mozgása:\n" +
                "\t1.Gyalogos (Pawn) – Csak előre, jobbra-előre, és balra-előre tudd lépni. Bábut leszedni csak balra-előre vagy jobbra-előre lépéssel tud\n" +
                "\t\tSpeciális lépések:\t\n" +
                "\t\ti.Dupla lépés – ha még nem lépett a gyalogos, akkor van opciója kettőt lépni előre, egy helyett \n" +
                "\t\tii.En Passant – ha egy ellentétes szinű gyalogos Dupla lépéssel a mi gyalogunk mellé került, akkor léphetünk fölé, ezzel leszedve ezt a gyalogost.\n" +
                "\t\tiii.Csere: Ha beér a tul oldal utolsó sorába akkor lecserélhető egy Királynőre, Futóra, Lovasra vagy Bástyára\n" +
                "\t2.Bástya (Rook) – csak vizszintesen vagy függőlegesen tud lépni, de bármennyit\n" +
                "\t\tSpeciális lépés: lásd. Sánc\n" +
                "\t3.Huszár (Knight) – csak L alakban tud lépni (2-t előre vagy hátra és 1-et jobbra vagy balra, vagy 2-t jobbra vagy balra és 1-et fel vagy le)\n" +
                "\t4.Futó (Bishop) – Csak átlósan tud lépni, de bármennyit\n" +
                "\t5.Király (King) – Bármelyik irányba tud lépni, de csak 1-et\n" +
                "\t\tSpeciális lépés: lásd. Sánc\n" +
                "\t6.Királynő (Queen) – Bármelyik irányba tud lépni, de csak 1-et\n" +
                "Fogalmak:\n" +
                "\t-Sánc: Ha a király és az egyik bástya nem lépett, és a közöttük levő terület üres, akkor: (Fehér király esetén, feketénél forditva)\n" +
                "\t-A jobb bástya esetén a király megy a bástya jobb oldalán lévő, a bástya meg a király bal oldalán lévő helyre\n" +
                "\t- A bal bástya esetén a király megy a bástyától jobbra 2. mezőre, a bástya meg a király jobb oldalán lávő helyre\n" +
                "\t-Sakk: Valamelyik bábú veszélyezteti a királyt (le tudná szedni, ha más bábú lenne király helyett\n" +
                "\n" +
                "A játék célja a király beszoritása. A játék 3 módon érhet véget:\n" +
                "\t1.Matt – sakkban van a király és nem lehet kiszabaditani vagy megoldani a sakkot\n" +
                "\t2.Feladás (Forfeit) – egyik játékos feladja\n" +
                "\t3.Döntetlen:  i. Ha 50 körig nincs bábúütés vagy gyalogoslépés, akkor akármelyik játékos kérhet döntetlent\n" +
                "\t\tii.Ha a király ugy akad be valahova hogy nincs más bábúja a játékosnak a táblán, és nincs sakkban (Patt)\n" +
                "\t\tiii.játékosok megeggyezésével\n" +
                "\n" +
                "Bábuk értéke:\n" +
                "\tGyalogos – 1\n" +
                "\tHuszár – 3\n" +
                "\tFutó – 3\n" +
                "\tBástya – 5\n" +
                "\tKirálynő – 9\n" +
                "\tKirály – nem számoljuk\n";

        setTitle("Rules");
        setSize(1200,800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JTextPane textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();
        Style style = doc.addStyle("CustomStyle", null);
        StyleConstants.setFontFamily(style, "Arial");
        StyleConstants.setFontSize(style, 14);
        textPane.setEditable(false);
        try {
            doc.insertString(0, text, style);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        add(textPane);

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            dispose();
        });
        back.setBorder(BorderFactory.createEmptyBorder(50, 350, 50, 350));
        back.setBackground(Color.GREEN);
        add(back, BorderLayout.SOUTH);
    }
}
