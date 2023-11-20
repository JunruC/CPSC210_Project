package ui.tabs;

import model.TradeList;
import persistence.Read;
import persistence.Save;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadData extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private JLabel question;
    private JRadioButton option1;
    private JRadioButton option2;

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
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(option1);
        buttonGroup.add(option2);
        ActionListener radioListener = e -> checkDecision(tl);
        option1.addActionListener(radioListener);
        option2.addActionListener(radioListener);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(question);
        panel.add(option1);
        panel.add(option2);
        add(panel);
        setVisible(true);
    }

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
