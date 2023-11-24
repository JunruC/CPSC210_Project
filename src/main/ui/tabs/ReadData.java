package ui.tabs;

import model.TradeList;
import persistence.Read;
import persistence.Save;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the tab when reading the data.
public class ReadData extends JFrame {
    public static final int WIDTH = 400;   // width of the tab
    public static final int HEIGHT = 600;  // height of the tab
    private JLabel question;               // question in the tab
    private JRadioButton option1;          // option one in the tab
    private JRadioButton option2;          // option two in the tab

    // Effects: construct the tab with title, size, question, and options.
    public ReadData() {
        TradeList tl = new TradeList();
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        question = new JLabel("Do you want to reload the recent progress?");
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
            readDataOptionOne(tl);
        } else if (option2.isSelected()) {
            dispose();
            readDataOptionTwo(tl);
        }
    }

    // Effects: process option one when read data.
    public void readDataOptionOne(TradeList tl) {
        Read r = new Read();
        try {
            r.readPendingTrades(tl, "PendingTradesFile.json");
            r.readCompletedTrades(tl, "CompletedTradesFile.json");
            r.readPendingTeamOne(tl, "PendingTeamOneFile.json");
            r.readPendingTeamTwo(tl, "PendingTeamTwoFile.json");
            r.readCompletedTeamOne(tl, "CompletedTeamOneFile.json");
            r.readCompletedTeamTwo(tl, "CompletedTeamTwoFile.json");
            r.readSuccess(tl, "SuccessFile.json");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read the data.");
        } finally {
            dispose();
            new HomePage(tl);
        }
    }

    // Effects: process option two when read data.
    public void readDataOptionTwo(TradeList tl) {
        Save s = new Save();
        try {
            s.clearData("PendingTradesFile.json","CompletedTradesFile.json","PendingTeamOneFile.json",
                    "PendingTeamTwoFile.json","CompletedTeamOneFile.json","CompletedTeamTwoFile.json",
                    "SuccessFile.json");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to clear previous data.");
        } finally {
            dispose();
            new HomePage(tl);
        }
    }
}
