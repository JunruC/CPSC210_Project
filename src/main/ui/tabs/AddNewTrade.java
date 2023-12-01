package ui.tabs;

import model.Trade;
import model.TradeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents the tab when adding new trade.
public class AddNewTrade extends JFrame {
    public static final int WIDTH = 400;   // width of the tab
    public static final int HEIGHT = 600;  // height of the tab
    private JLabel question;               // question in the tab
    private JRadioButton option1;          // option one in the tab
    private JRadioButton option2;          // option two in the tab

    // Effects: construct the tab with title, size, question, and options.
    public AddNewTrade(TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        question = new JLabel("Add another trade?");
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
            Trade t0 = new Trade();
            tl.addPendingTrade(t0);
            new MakeFirstTeam(tl);
        } else if (option2.isSelected()) {
            dispose();
            new HomePage(tl);
        }
    }
}
