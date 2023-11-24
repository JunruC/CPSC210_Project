package ui.tabs;

import model.TradeList;

import javax.swing.*;
import java.awt.*;

// Represents the tab when displaying trade results.
public class TradeResults extends JFrame {
    public static final int WIDTH = 400;   // width of the tab
    public static final int HEIGHT = 600;  // height of the tab
    private JTextArea display;             // display area in the tab
    private JButton submit;                // ok button in the tab

    // Effects: construct the tab with title, size, display area, and ok button.
    public TradeResults(TradeList tl, StringBuilder sb) {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextArea(sb.toString());
        display.setEditable(false);
        display.setLineWrap(true);
        display.setWrapStyleWord(true);
        submit = new JButton("OK");

        submit.addActionListener(e -> {
            dispose();
            new HomePage(tl);
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        JLabel image = new JLabel(new ImageIcon("data/NBA_logo.png"));
        panel.add(image);
        panel.add(new JScrollPane(display));
        panel.add(submit);

        getContentPane().add(panel);
        setVisible(true);
    }
}
