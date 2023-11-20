package ui.tabs;

import model.Player;
import model.Team;
import model.Trade;
import model.TradeList;

import javax.swing.*;
import java.awt.*;

public class FirstTeamOptionThree extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private JLabel question;
    private JTextField answer;
    private JButton submit;

    public FirstTeamOptionThree(Team t, TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        question = new JLabel("Enter the name of the player you want to send away:");
        answer = new JTextField(1);
        submit = new JButton("OK");

        submit.addActionListener(e -> {
            String decision = answer.getText();
            checkDecision(tl, decision, t);
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(question);
        panel.add(answer);
        panel.add(submit);

        getContentPane().add(panel);
        setVisible(true);
    }

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
        new ProcessFirstTeam(t, tl);
    }
}
