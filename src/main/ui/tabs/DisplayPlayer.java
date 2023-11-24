package ui.tabs;

import model.Player;
import model.Team;
import model.TradeList;

import javax.swing.*;
import java.awt.*;

// Represents the tab when displaying player information.
public class DisplayPlayer extends JFrame {
    public static final int WIDTH = 400;   // width of the tab
    public static final int HEIGHT = 600;  // height of the tab
    private JTextArea display;             // display area in the tab
    private JButton submit;                // ok button in the tab

    // Requires: i = 1 or 2
    // Effects: construct the tab with title, size, display area, and ok button.
    public DisplayPlayer(Player p, int i, Team t, TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display = new JTextArea(printPlayerInformation(p));
        display.setEditable(false);
        display.setLineWrap(true);
        display.setWrapStyleWord(true);
        submit = new JButton("OK");
        submit.addActionListener(e -> {
            dispose();
            checkDecision(i,t,tl);
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

    // Effects: print the information of the player.
    public String printPlayerInformation(Player p) {
        StringBuilder sb = new StringBuilder();
        sb.append("Team: " + p.getTeam()).append("\n");
        sb.append("Name: " + p.getName()).append("\n");
        sb.append("Position: " + p.getPosition()).append("\n");
        sb.append("Age: " + p.getAge()).append("\n");
        sb.append("Height: " + p.getHeight() + "cm").append("\n");
        sb.append("Weight: " + p.getWeight() + "kg").append("\n");
        sb.append("Salary: " + p.getSalary() + " million dollars").append("\n");
        return sb.toString();
    }

    // Effects: guide to the new tab according to the user decision.
    public void checkDecision(int i,Team t,TradeList tl) {
        if (i == 1) {
            new ProcessFirstTeam(t,tl);
        } else if (i == 2) {
            new ProcessSecondTeam(t,tl);
        }
    }
}
