package ui.tabs;

import model.Player;
import model.Team;
import model.TradeList;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MakeFirstTeam extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private JLabel question;
    private JTextField answer;
    private JButton submit;

    public MakeFirstTeam(TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        question = new JLabel("Enter the team you want to send players away:");
        answer = new JTextField(1);
        submit = new JButton("OK");

        submit.addActionListener(e -> {
            String decision = answer.getText();
            checkDecision(tl, decision);
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(question);
        panel.add(answer);
        panel.add(submit);

        getContentPane().add(panel);
        setVisible(true);
    }

    public void checkDecision(TradeList tl, String teamName) {
        String line;
        String splitBy = ",";
        Team t1 = new Team(teamName);
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/" + teamName + ".csv"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                Player p = new Player(data[0],data[1],data[2],Integer.parseInt(data[3]),Integer.parseInt(data[4]),
                        Double.parseDouble(data[5]),Double.parseDouble(data[6]));
                t1.getPlayers().add(p);
            }
            tl.getPendingTeamOne().add(t1);
            answer = null;
            dispose();
            new ProcessFirstTeam(t1, tl);
        } catch (IOException e) {
            answer = null;
            dispose();
            JOptionPane.showMessageDialog(this, "Team does not exist.");
            new MakeFirstTeam(tl);
        }
    }
}
