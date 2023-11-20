package ui.tabs;

import model.Team;
import model.TradeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProcessFirstTeam extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private JLabel question;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;

    public ProcessFirstTeam(Team t, TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        printQuestion();
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(option1);
        buttonGroup.add(option2);
        buttonGroup.add(option3);
        buttonGroup.add(option4);
        ActionListener radioListener = e -> checkDecision(t,tl);
        option1.addActionListener(radioListener);
        option2.addActionListener(radioListener);
        option3.addActionListener(radioListener);
        option4.addActionListener(radioListener);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(question);
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        add(panel);
        setVisible(true);
    }

    public void checkDecision(Team t, TradeList tl) {
        if (option1.isSelected()) {
            dispose();
            new FirstTeamOptionOne(t,tl);
        } else if (option2.isSelected()) {
            dispose();
            new FirstTeamOptionTwo(t,tl);
        } else if (option3.isSelected()) {
            dispose();
            new FirstTeamOptionThree(t,tl);
        } else if (option4.isSelected()) {
            dispose();
            new MakeSecondTeam(tl);
        }
    }

    public void printQuestion() {
        question = new JLabel("Select an option:");
        option1 = new JRadioButton("View the team");
        option2 = new JRadioButton("View the player");
        option3 = new JRadioButton("Enter the player you want to send away");
        option4 = new JRadioButton("Choose the other team");
        option1.setFocusable(false);
        option2.setFocusable(false);
        option3.setFocusable(false);
        option4.setFocusable(false);
    }
}
