package ui;

import ui.tabs.ReadData;

import javax.swing.*;

public class NbaPlayerTradingSimulatorUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReadData().setVisible(true));
    }
}
