package ui.tabs;

import model.TradeList;
import persistence.Save;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SaveData extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private JLabel question;
    private JRadioButton option1;
    private JRadioButton option2;

    public SaveData(TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        question = new JLabel("Do you want to save the current progress?");
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
            saveDataOptionOne(tl);
            System.exit(0);
        } else if (option2.isSelected()) {
            dispose();
            JOptionPane.showMessageDialog(this, "Quit successfully.");
            System.exit(0);
        }
    }

    // Effects: process option one when save data.
    public void saveDataOptionOne(TradeList tl) {
        Save s = new Save();
        try {
            s.savePendingTrades(tl, "PendingTradesFile.json");
            s.saveCompletedTrades(tl, "CompletedTradesFile.json");
            s.savePendingTeamOne(tl, "PendingTeamOneFile.json");
            s.savePendingTeamTwo(tl, "PendingTeamTwoFile.json");
            s.saveCompletedTeamOne(tl, "CompletedTeamOneFile.json");
            s.saveCompletedTeamTwo(tl, "CompletedTeamTwoFile.json");
            s.saveSuccess(tl, "SuccessFile.json");
            JOptionPane.showMessageDialog(this, "Saved successfully.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save the data.");
        } finally {
            JOptionPane.showMessageDialog(this, "Quit successfully.");
        }
    }
}
