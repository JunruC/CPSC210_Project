package ui.tabs;

import model.Player;
import model.Team;
import model.TradeList;

import javax.swing.*;
import java.awt.*;

public class SecondTeamOptionTwo extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private JLabel question;
    private JTextField answer;
    private JButton submit;

    public SecondTeamOptionTwo(Team t, TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        question = new JLabel("Enter the name of the player you want to view:");
        answer = new JTextField(1);
        submit = new JButton("OK");

        submit.addActionListener(e -> {
            String decision = answer.getText();
            checkDecision(t, decision, tl);
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(question);
        panel.add(answer);
        panel.add(submit);

        getContentPane().add(panel);
        setVisible(true);
    }

    public void checkDecision(Team t, String name, TradeList tl) {
        boolean findPlayer = false;
        for (Player p : t.getPlayers()) {
            if (name.equals(p.getName())) {
                answer = null;
                dispose();
                new DisplayPlayer(p,2,t,tl);
                findPlayer = true;
                break;
            }
        }
        if (!findPlayer) {
            answer = null;
            dispose();
            JOptionPane.showMessageDialog(this, "Player not found.");
            new ProcessSecondTeam(t,tl);
        }
    }
}
