package ui.tabs;

import model.Player;
import model.Team;
import model.Trade;
import model.TradeList;

import javax.swing.*;
import java.awt.*;

// Represents the tab when viewing completed trades.
public class ViewCompletedTrades extends JFrame {
    public static final int WIDTH = 400;     // width of the tab
    public static final int HEIGHT = 1200;   // height of the tab
    private JTextArea display;               // display are in the tab
    private JButton submit;                  // ok button in the tab
    public static final String TAB = "    "; // indent

    // Effects: construct the tab with title, size, display area, and ok button.
    public ViewCompletedTrades(TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextArea(printCompletedTrades(tl));
        display.setEditable(false);
        display.setLineWrap(true);
        display.setWrapStyleWord(true);
        submit = new JButton("OK");

        submit.addActionListener(e -> {
            dispose();
            new HomePage(tl);
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

    // Effects: print the information of completed trades.
    public String printCompletedTrades(TradeList tl) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tl.getCompletedTrades().size(); i++) {
            sb.append("Trade " + (i + 1) + ": ").append("\n");
            if (tl.getSuccess().get(i)) {
                Trade trade = tl.getCompletedTrades().get(i);
                Team t1 = tl.getCompletedTeamOne().get(i);
                Team t2 = tl.getCompletedTeamTwo().get(i);
                for (int j = 0; j < trade.getTradedPlayers().size(); j++) {
                    Player p = trade.getTradedPlayers().get(j);
                    if (p.getTeam().equals(t1.getTeamName())) {
                        sb.append(TAB + p.getName() + " goes to " + t2.getTeamName() + ".").append("\n");
                    } else {
                        sb.append(TAB + p.getName() + " goes to " + t1.getTeamName() + ".").append("\n");
                    }
                }
                sb.append("\n");
                tradeReport(t1, t2, sb);
            } else {
                sb.append(TAB + "This trade failed.").append("\n");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    // Effects: print the report of the trade.
    public void tradeReport(Team t1, Team t2, StringBuilder sb) {
        t1.calculateTotalSalary();
        sb.append(TAB + "New group of players in " + t1.getTeamName() + " are:").append("\n");
        for (Player p : t1.getPlayers()) {
            sb.append(TAB + TAB + p.getName()).append("\n");
        }
        sb.append("\n");
        sb.append(TAB + "Salary of " + t1.getTeamName() + ": " + t1.getTotalSalary() + " million dollars.\n");
        if (t1.isSalaryAboveTaxLine()) {
            sb.append(TAB + t1.getTeamName() + " will have to pay luxury tax.").append("\n");
        }
        sb.append("\n");
        t2.calculateTotalSalary();
        sb.append(TAB + "New group of players in " + t2.getTeamName() + " are:").append("\n");
        for (Player p : t2.getPlayers()) {
            sb.append(TAB + TAB + p.getName()).append("\n");
        }
        sb.append("\n");
        sb.append(TAB + "Salary of " + t2.getTeamName() + ": " + t2.getTotalSalary() + " million dollars.\n");
        if (t2.isSalaryAboveTaxLine()) {
            sb.append(TAB + t2.getTeamName() + " will have to pay luxury tax.").append("\n");
        }
        sb.append("\n");
    }
}
