package ui.tabs;

import model.Player;
import model.Team;
import model.TradeList;

import javax.swing.*;
import java.awt.*;

public class DisplayPlayer extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 400;
    private JTextArea display;
    private JButton submit;

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
            if (i == 1) {
                new ProcessFirstTeam(t,tl);
            } else if (i == 2) {
                new ProcessSecondTeam(t,tl);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
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
}
