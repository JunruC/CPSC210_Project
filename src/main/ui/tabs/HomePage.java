package ui.tabs;

import model.Trade;
import model.TradeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents the tab of the homepage.
public class HomePage extends JFrame {
    public static final int WIDTH = 400;   // width of the tab
    public static final int HEIGHT = 600;  // height of the tab
    private JLabel question;               // question in the tab
    private JRadioButton option1;          // option one in the tab
    private JRadioButton option2;          // option two in the tab
    private JRadioButton option3;          // option three in the tab
    private JRadioButton option4;          // option four in the tab

    // Effects: construct the tab with title, size, question, and options.
    public HomePage(TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        printQuestion();
        ActionListener radioListener = e -> checkDecision(tl);
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
    public void checkDecision(TradeList tl) {
        if (option4.isSelected()) {
            dispose();
            new SaveData(tl);
        } else if (option1.isSelected()) {
            dispose();
            Trade t = new Trade();
            tl.getPendingTrades().add(t);
            new MakeFirstTeam(tl);
        } else if (option2.isSelected()) {
            doOption2(tl);
        } else if (option3.isSelected()) {
            doOption3(tl);
        }
    }

    // Effects: print the question and options.
    public void printQuestion() {
        question = new JLabel("Select an option:");
        option1 = new JRadioButton("Add a trade");
        option2 = new JRadioButton("View pending trades");
        option3 = new JRadioButton("View completed trades");
        option4 = new JRadioButton("Quit");
        option1.setFocusable(false);
        option2.setFocusable(false);
        option3.setFocusable(false);
        option4.setFocusable(false);
    }

    // Effects: guide to the new tab when option two is selected.
    public void doOption2(TradeList tl) {
        dispose();
        if (tl.getPendingTrades().size() == 0) {
            JOptionPane.showMessageDialog(this, "There is no pending trade now.");
            new HomePage(tl);
        } else {
            new ViewPendingTrades(tl);
        }
    }

    // Effects: guide to the new tab when option three is selected.
    public void doOption3(TradeList tl) {
        dispose();
        if (tl.getCompletedTrades().size() == 0) {
            JOptionPane.showMessageDialog(this, "No trade completed yet.");
            new HomePage(tl);
        } else {
            new ViewCompletedTrades(tl);
        }
    }
}
