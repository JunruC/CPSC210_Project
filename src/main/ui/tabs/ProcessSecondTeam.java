package ui.tabs;

import model.Team;
import model.TradeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents the tab when processing the second team.
public class ProcessSecondTeam extends JFrame {
    public static final int WIDTH = 400;   // width of the tab
    public static final int HEIGHT = 600;  // height of the tab
    private JLabel question;               // question in the tab
    private JRadioButton option1;          // option one in the tab
    private JRadioButton option2;          // option two in the tab
    private JRadioButton option3;          // option three in tha tab
    private JRadioButton option4;          // option four in the tab

    // Effects: construct the tab with title, size, question, and options.
    public ProcessSecondTeam(Team t, TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        printQuestion();
        ActionListener radioListener = e -> checkDecision(t,tl);
        option1.addActionListener(radioListener);
        option2.addActionListener(radioListener);
        option3.addActionListener(radioListener);
        option4.addActionListener(radioListener);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));
        JLabel image = new JLabel(new ImageIcon("data/NBA_logo.png"));
        panel.add(image);
        panel.add(question);
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        add(panel);
        setVisible(true);
    }

    // Effects: guide to the new tab according to the user decision.
    public void checkDecision(Team t, TradeList tl) {
        if (option1.isSelected()) {
            dispose();
            new SecondTeamOptionOne(t,tl);
        } else if (option2.isSelected()) {
            dispose();
            new SecondTeamOptionTwo(t,tl);
        } else if (option3.isSelected()) {
            dispose();
            new SecondTeamOptionThree(t,tl);
        } else if (option4.isSelected()) {
            dispose();
            new AddNewTrade(tl);
        }
    }

    // Effects: print the question and options.
    public void printQuestion() {
        question = new JLabel("Select an option:");
        option1 = new JRadioButton("View the team");
        option2 = new JRadioButton("View the player");
        option3 = new JRadioButton("Enter the player you want to get from");
        option4 = new JRadioButton("Finished adding players");
        option1.setFocusable(false);
        option2.setFocusable(false);
        option3.setFocusable(false);
        option4.setFocusable(false);
    }
}
