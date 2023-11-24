package ui.tabs;

import model.Player;
import model.Team;
import model.TradeList;

import javax.swing.*;
import java.awt.*;

// Represents the tab when processing option one of the first team.
public class FirstTeamOptionOne extends JFrame {
    public static final int WIDTH = 400;    // width of the tab
    public static final int HEIGHT = 1000;  // height of the tab
    private JTextArea display;              // display area in the tab
    private JButton submit;                 // ok button in the tab

    // Effects: construct the tab with title, size, display area, and ok button.
    public FirstTeamOptionOne(Team t, TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextArea(displayPlayers(t));
        display.setEditable(false);
        display.setLineWrap(true);
        display.setWrapStyleWord(true);
        submit = new JButton("OK");

        submit.addActionListener(e -> {
            dispose();
            new ProcessFirstTeam(t,tl);
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

    // Effects: display the players in the team.
    public String displayPlayers(Team t) {
        StringBuilder sb = new StringBuilder();
        for (Player p : t.getPlayers()) {
            sb.append(p.getName()).append("\n");
        }
        return sb.toString();
    }
}
