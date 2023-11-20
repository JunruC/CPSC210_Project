package ui.tabs;

import model.Trade;
import model.TradeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private JLabel question;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;

    public HomePage(TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        printQuestion();
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(option1);
        buttonGroup.add(option2);
        buttonGroup.add(option3);
        buttonGroup.add(option4);
        ActionListener radioListener = e -> checkDecision(tl);
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

    public void doOption2(TradeList tl) {
        dispose();
        if (tl.getPendingTrades().size() == 0) {
            JOptionPane.showMessageDialog(this, "There is no pending trade now.");
            new HomePage(tl);
        } else {
            new ViewPendingTrades(tl);
        }
    }

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
