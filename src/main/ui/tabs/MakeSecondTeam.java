package ui.tabs;

import model.Player;
import model.Team;
import model.TradeList;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Represents the tab when making the second team.
public class MakeSecondTeam extends JFrame {
    public static final int WIDTH = 400;   // width of the tab
    public static final int HEIGHT = 600;  // height of the tab
    private JLabel question;               // question in the tab
    private JTextField answer;             // answer area in the tab
    private JButton submit;                // submit button in the tab

    // Effects: construct the tab with title, size, question, answer area, and submit button.
    public MakeSecondTeam(TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        question = new JLabel("Enter the team you want to get players from:");
        answer = new JTextField(1);
        submit = new JButton("OK");

        submit.addActionListener(e -> {
            String decision = answer.getText();
            checkDecision(tl, decision);
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
    public void checkDecision(TradeList tl, String teamName) {
        String line;
        String splitBy = ",";
        Team t2 = new Team(teamName);
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/" + teamName + ".csv"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                Player p = new Player(data[0],data[1],data[2],Integer.parseInt(data[3]),Integer.parseInt(data[4]),
                        Double.parseDouble(data[5]),Double.parseDouble(data[6]));
                t2.getPlayers().add(p);
            }
            tl.getPendingTeamTwo().add(t2);
            answer = null;
            dispose();
            new ProcessSecondTeam(t2, tl);
        } catch (IOException e) {
            answer = null;
            dispose();
            JOptionPane.showMessageDialog(this, "Team does not exist.");
            new MakeSecondTeam(tl);
        }
    }
}
