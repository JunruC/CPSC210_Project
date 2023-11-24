package ui.tabs;

import model.Team;
import model.Trade;
import model.TradeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents the tab when conducting trades.
public class ConductTrades extends JFrame {
    public static final int WIDTH = 400;   // width of the tab
    public static final int HEIGHT = 600;  // height of the tab
    private JLabel question;               // question in the tab
    private JRadioButton option1;          // option one in the tab
    private JRadioButton option2;          // option two in the tab

    // Effects: construct the tab with title, size, question, and options.
    public ConductTrades(TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        question = new JLabel("Do you want to conduct these trades?");
        option1 = new JRadioButton("Yes");
        option2 = new JRadioButton("No");
        option1.setFocusable(false);
        option2.setFocusable(false);
        ActionListener radioListener = e -> checkDecision(tl);
        option1.addActionListener(radioListener);
        option2.addActionListener(radioListener);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        JLabel image = new JLabel(new ImageIcon("data/NBA_logo.png"));
        panel.add(image);
        panel.add(question);
        panel.add(option1);
        panel.add(option2);
        add(panel);
        setVisible(true);
    }

    // Effects: guide to the new tab according to the user decision.
    public void checkDecision(TradeList tl) {
        if (option1.isSelected()) {
            dispose();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tl.getPendingTrades().size(); i++) {
                processTrade(tl, i, sb);
            }
            tl.getPendingTrades().clear();
            tl.getPendingTeamOne().clear();
            tl.getPendingTeamTwo().clear();
            new TradeResults(tl,sb);
        } else if (option2.isSelected()) {
            dispose();
            new HomePage(tl);
        }
    }

    // Requires: i >= 0
    // Modifies: tl, sb
    // Effects: process the selected trade.
    public void processTrade(TradeList tl, int i, StringBuilder sb) {
        Trade tradeInProcess = tl.getPendingTrades().get(i);
        sb.append("Trade No." + (i + 1) + ":").append("\n");
        Team ct1 = new Team(tl.getPendingTeamOne().get(i).getTeamName());
        for (int j = 0; j < tl.getPendingTeamOne().get(i).getPlayers().size(); j++) {
            ct1.getPlayers().add(tl.getPendingTeamOne().get(i).getPlayers().get(j));
        }
        Team ct2 = new Team(tl.getPendingTeamTwo().get(i).getTeamName());
        for (int k = 0; k < tl.getPendingTeamTwo().get(i).getPlayers().size(); k++) {
            ct2.getPlayers().add(tl.getPendingTeamTwo().get(i).getPlayers().get(k));
        }
        Boolean success = tradeInProcess.conductTrade(tl.getPendingTeamOne().get(i),
                tl.getPendingTeamTwo().get(i), tradeInProcess);
        tl.getSuccess().add(success);
        tl.getCompletedTrades().add(tradeInProcess);
        if (success) {
            tl.getCompletedTeamOne().add(tl.getPendingTeamOne().get(i));
            tl.getCompletedTeamTwo().add(tl.getPendingTeamTwo().get(i));
            sb.append("Trade completed!").append("\n");
        } else {
            tl.getCompletedTeamOne().add(ct1);
            tl.getCompletedTeamTwo().add(ct2);
            sb.append("Trade failed.").append("\n");
        }
    }
}
