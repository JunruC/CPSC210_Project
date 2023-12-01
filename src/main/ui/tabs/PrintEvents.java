package ui.tabs;

import model.EventLog;
import model.Event;

import javax.swing.*;
import java.awt.*;

// Represents the tab when printing out events.
public class PrintEvents extends JFrame {
    public static final int WIDTH = 600;   // width of the tab
    public static final int HEIGHT = 600;  // height of the tab
    private JTextArea display;             // display area in the tab
    private JButton submit;                // ok button in the tab

    // Effects: construct the tab with title, size, display area, and ok button.
    public PrintEvents() {
        setTitle("NBA Player Trading Simulator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display = new JTextArea(allEvents());
        display.setEditable(false);
        display.setLineWrap(true);
        display.setWrapStyleWord(true);
        submit = new JButton("OK");
        submit.addActionListener(e -> {
            dispose();
            System.exit(0);
        });
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(new JScrollPane(display));
        panel.add(submit);
        getContentPane().add(panel);
        setVisible(true);
    }

    // Effects: print all the events in a time sequence.
    public String allEvents() {
        StringBuilder sb = new StringBuilder();
        for (Event e : EventLog.getInstance()) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}
