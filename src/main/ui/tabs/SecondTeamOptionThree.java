package ui.tabs;

import model.Player;
import model.Team;
import model.Trade;
import model.TradeList;

import javax.swing.*;
import java.awt.*;

// Represents the tab when processing option three of the second team.
public class SecondTeamOptionThree extends JFrame {
    public static final int WIDTH = 400;   // width of the tab
    public static final int HEIGHT = 600;  // height of the tab
    private JLabel question;               // question in the tab
    private JTextField answer;             // answer area in the tab
    private JButton submit;                // submit button in the tab

    // Effects: construct the tab with title, size, question, answer area, and submit button.
    public SecondTeamOptionThree(Team t, TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        question = new JLabel("Enter the name of the player you want to get from:");
        answer = new JTextField(1);
        submit = new JButton("OK");

        submit.addActionListener(e -> {
            String decision = answer.getText();
            checkDecision(tl, decision, t);
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        JLabel image = new JLabel(new ImageIcon("data/NBA_logo.png"));
        panel.add(image);
        panel.add(question);
        panel.add(answer);
        panel.add(submit);

        getContentPane().add(panel);
        setVisible(true);
    }

    // Effects: guide to the new tab according to the user decision.
    public void checkDecision(TradeList tl, String player, Team t) {
        boolean playerInTeam = false;
        for (Player p : t.getPlayers()) {
            if (player.equals(p.getName())) {
                Trade trade = tl.getPendingTrades().get(tl.getPendingTrades().size() - 1);
                trade.getTradedPlayers().add(p);
                JOptionPane.showMessageDialog(this, "Player added successfully.");
                playerInTeam = true;
                break;
            }
        }
        if (!playerInTeam) {
            JOptionPane.showMessageDialog(this, "Player not found.");
        }
        answer = null;
        dispose();
        new ProcessSecondTeam(t, tl);
    }
}
