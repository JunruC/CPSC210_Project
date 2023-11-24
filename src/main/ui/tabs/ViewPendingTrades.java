package ui.tabs;

import model.Player;
import model.TradeList;

import javax.swing.*;
import java.awt.*;

// Represents the tab when viewing pending trades.
public class ViewPendingTrades extends JFrame {
    public static final int WIDTH = 400;      // width of the tab
    public static final int HEIGHT = 800;     // height of the tab
    private JTextArea display;                // display area in the tab
    private JButton submit;                   // ok button in the tab
    public static final String TAB = "    ";  // indent

    // Effects: construct the tab with title, size, display area, and ok button.
    public ViewPendingTrades(TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextArea(printPendingTrades(tl));
        display.setEditable(false);
        display.setLineWrap(true);
        display.setWrapStyleWord(true);
        submit = new JButton("OK");

        submit.addActionListener(e -> {
            dispose();
            new ConductTrades(tl);
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        JLabel image = new JLabel(new ImageIcon("data/NBA_logo.png"));
        panel.add(image);
        panel.add(new JScrollPane(display));
        panel.add(submit);

        getContentPane().add(panel);
        setVisible(true);
    }

    // Effects: print the information of pending trades.
    public String printPendingTrades(TradeList tl) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < tl.getPendingTrades().size(); j++) {
            sb.append("Players in trade No." + (j + 1) + ": ").append("\n");
            for (Player p : tl.getPendingTrades().get(j).getTradedPlayers()) {
                sb.append(TAB + p.getName() + " from " + p.getTeam()).append("\n");
            }
        }
        return sb.toString();
    }
}
