package ui.tabs;

import model.TradeList;
import persistence.Save;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Represents the tab when saving the data.
public class SaveData extends JFrame {
    public static final int WIDTH = 400;   // width of the tab
    public static final int HEIGHT = 600;  // height of the tab
    private JLabel question;               // question in the tab
    private JRadioButton option1;          // option one in the tab
    private JRadioButton option2;          // option two in the tab

    // Effects: construct the tab with title, size, question, and options.
    public SaveData(TradeList tl) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        question = new JLabel("Do you want to save the current progress?");
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
