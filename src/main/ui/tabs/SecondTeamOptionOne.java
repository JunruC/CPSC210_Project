package ui.tabs;

import model.Player;
import model.Team;
import model.TradeList;

import javax.swing.*;
import java.awt.*;

public class SecondTeamOptionOne extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 700;
    private JTextArea display;
    private JButton submit;

    public SecondTeamOptionOne(Team t, TradeList tl) {
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
            new ProcessSecondTeam(t,tl);
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(new JScrollPane(display));
        panel.add(submit);

        getContentPane().add(panel);
        setVisible(true);
    }

    public String displayPlayers(Team t) {
        StringBuilder sb = new StringBuilder();
        for (Player p : t.getPlayers()) {
            sb.append(p.getName()).append("\n");
        }
        return sb.toString();
    }
}
