package ui;

import model.Player;
import model.Team;
import model.Trade;
import model.TradeList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// NBA Player Trading Simulator Application
public class NbaPlayerTradingSimulator {

    // Effects: run the NBA Player Trading Simulator Application.
    public NbaPlayerTradingSimulator() {
        TradeList tl = new TradeList();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            printOptions();
            int option = scanner.nextInt();
            if (option == 4) {
                System.out.println("Quit successfully.");
                break;
            } else {
                switch (option) {
                    case 1:
                        optionOne(tl);
                        break;
                    case 2:
                        optionTwo(tl);
                        break;
                    case 3:
                        optionThree(tl);
                        break;
                }
            }
        }
    }

    // Effects: print 4 options to user.
    public void printOptions() {
        System.out.println("Select an option:");
        System.out.println("1. Add a trade");
        System.out.println("2. Conduct trades");
        System.out.println("3. View completed trades");
        System.out.println("4. Quit");
    }

    // Modifies: this
    // Effects: Process option 1.
    public void optionOne(TradeList tl) {
        makeFirstTeam(tl);
        makeSecondTeam(tl);
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Add another trade?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int input = sc.nextInt();
            if (input == 2) {
                break;
            }
            makeFirstTeam(tl);
            makeSecondTeam(tl);
        }
    }

    // Modifies: this
    // Effects: process option 2.
    public void optionTwo(TradeList tl) {
        if (tl.getPendingTradesSize() == 0) {
            System.out.println("There is no trade to be conducted now.");
        } else {
            for (int i = 0; i < tl.getPendingTradesSize(); i++) {
                Trade tradeInProcess = tl.getTradeFromPending(i);
                System.out.println("No." + (i + 1) + ": ");
                tradeInProcess.conductTrade(tl.getTeamOne(i), tl.getTeamTwo(i), tradeInProcess);
                tl.addTradeToCompleted(tradeInProcess);
            }
            tl.clearPendingTrades();
        }
        System.out.println("");
    }


    // Effects: process option 3.
    public void optionThree(TradeList tl) {
        for (int i = 0; i < tl.getCompletedTradesSize(); i++) {
            Trade trade = tl.getTradeFromCompleted(i);
            Team t1 = tl.getTeamOne(i);
            Team t2 = tl.getTeamTwo(i);
            for (int j = 0; j < trade.getTradeSize(); j++) {
                Player p = trade.getTradedPlayer(j);
                if (p.getTeam().equals(t1.getTeamName())) {
                    System.out.println(p.getName() + " goes to " + t2.getTeamName());
                } else {
                    System.out.println(p.getName() + " goes to " + t1.getTeamName());
                }
            }
            salaryReport(t1, t2);
        }
    }

    // Requires: t1 and t2 are different.
    // Modifies: this
    // Effects: calculate total salaries of both teams and then print reports.
    public void salaryReport(Team t1, Team t2) {
        t1.calculateTotalSalary();
        System.out.println("Salary of " + t1.getTeamName() + ": "
                + t1.getTotalSalary() + " million dollars.");
        if (t1.isSalaryAboveTaxLine()) {
            System.out.println(t1.getTeamName() + " will have to pay luxury tax.");
        }
        t2.calculateTotalSalary();
        System.out.println("Salary of " + t2.getTeamName() + ": "
                + t2.getTotalSalary() + " million dollars.");
        if (t2.isSalaryAboveTaxLine()) {
            System.out.println(t2.getTeamName() + " will have to pay luxury tax.");
        }
    }

    // Modifies: this
    // Effects: make the first side team of the trade and then process the first team.
    public void makeFirstTeam(TradeList tl) {
        String line;
        String splitBy = ",";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the team you want to send players away:");
        String teamName = scanner.nextLine();
        Team t1 = new Team(teamName);
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/" + teamName + ".csv"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                Player p = new Player(data[0],data[1],data[2],Integer.parseInt(data[3]),Integer.parseInt(data[4]),
                        Double.parseDouble(data[5]),Double.parseDouble(data[6]));
                t1.addPlayer(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        processFirstTeam(t1, tl);
    }

    // Modifies: this
    // Effects: make the second side team of the trade and then process the second team.
    public void makeSecondTeam(TradeList tl) {
        String line;
        String splitBy = ",";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the team you want to get players from:");
        String teamName = scanner.nextLine();
        Team t2 = new Team(teamName);
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/" + teamName + ".csv"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                Player p = new Player(data[0],data[1],data[2],Integer.parseInt(data[3]),Integer.parseInt(data[4]),
                        Double.parseDouble(data[5]),Double.parseDouble(data[6]));
                t2.addPlayer(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        processSecondTeam(t2, tl);
    }

    // Modifies: this
    // Effects: process the first team.
    public void processFirstTeam(Team t, TradeList tl) {
        Scanner scanner = new Scanner(System.in);
        firstTeamOptions();
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                firstTeamOptionOne(t, tl);
                break;
            case 2:
                firstTeamOptionTwo(t, tl);
                break;
            case 3:
                firstTeamOptionThree(t, tl);
                break;
            case 4:
                break;
        }
    }

    // Modifies: this
    // Effects: process the second team.
    public void processSecondTeam(Team t, TradeList tl) {
        Scanner scanner = new Scanner(System.in);
        secondTeamOptions();
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                secondTeamOptionOne(t, tl);
                break;
            case 2:
                secondTeamOptionTwo(t, tl);
                break;
            case 3:
                secondTeamOptionThree(t, tl);
                break;
            case 4:
                break;
        }
    }

    // Effects: print 4 options when process the first team.
    public void firstTeamOptions() {
        System.out.println("Select an option:");
        System.out.println("1. View the team");
        System.out.println("2. View the player");
        System.out.println("3. Enter the player you want to send away");
        System.out.println("4. Choose the other team");
    }

    // Effects: process option 1 when process the first team.
    public void firstTeamOptionOne(Team t, TradeList tl) {
        for (int i = 0; i < t.getTeamSize(); i++) {
            System.out.println(t.getPlayer(i).getName());
        }
        processFirstTeam(t, tl);
    }

    // Effects: process option 2 when process the first team.
    public void firstTeamOptionTwo(Team t, TradeList tl) {
        Scanner s2 = new Scanner(System.in);
        System.out.println("Enter the name of the player you want to view:");
        String name = s2.nextLine();
        boolean findPlayer = false;
        for (int i = 0; i < t.getTeamSize(); i++) {
            if (name.equals(t.getPlayer(i).getName())) {
                findPlayer = true;
                break;
            }
        }
        if (!findPlayer) {
            System.out.println("Player not found.");
        }
        processFirstTeam(t, tl);
    }

    // Modifies: this
    // Effects: process option 3 when process the first team.
    public void firstTeamOptionThree(Team t, TradeList tl) {
        Scanner s3 = new Scanner(System.in);
        System.out.println("Enter the name of the player you want to send away:");
        String player = s3.nextLine();
        Trade tp = new Trade();
        boolean playerInTeam = false;
        for (int i = 0; i < t.getTeamSize(); i++) {
            if (player.equals(t.getPlayer(i).getName())) {
                tp.addPlayerToTrade(t.getPlayer(i));
                tl.addTradeToPending(tp);
                tl.addTeamOne(t);
                playerInTeam = true;
                break;
            }
        }
        if (!playerInTeam) {
            System.out.println("Player not found.");
        }
        processFirstTeam(t, tl);
    }

    // Effects: print 4 options when process the second team.
    public void secondTeamOptions() {
        System.out.println("Select an option:");
        System.out.println("1. View the team");
        System.out.println("2. View the player");
        System.out.println("3. Enter the player you want to get from");
        System.out.println("4. Finished adding players");
    }

    // Effects: process option 1 when process the second team.
    public void secondTeamOptionOne(Team t, TradeList tl) {
        for (int i = 0; i < t.getTeamSize(); i++) {
            System.out.println(t.getPlayer(i).getName());
        }
        processSecondTeam(t, tl);
    }

    // Effects: process option 2 when process the second team.
    public void secondTeamOptionTwo(Team t, TradeList tl) {
        Scanner s2 = new Scanner(System.in);
        System.out.println("Enter the name of the player you want to view:");
        String name = s2.nextLine();
        boolean findPlayer = false;
        for (int i = 0; i < t.getTeamSize(); i++) {
            if (name.equals(t.getPlayer(i).getName())) {
                printPlayerInformation(t, i);
                findPlayer = true;
                break;
            }
        }
        if (!findPlayer) {
            System.out.println("Player not found.");
        }
        processSecondTeam(t, tl);
    }

    // Modifies: this
    // Effects: process option 3 when process the second team.
    public void secondTeamOptionThree(Team t, TradeList tl) {
        Scanner s3 = new Scanner(System.in);
        System.out.println("Enter the name of the player you want to send away:");
        String player = s3.nextLine();
        boolean playerInTeam = false;
        for (int i = 0; i < t.getTeamSize(); i++) {
            if (player.equals(t.getPlayer(i).getName())) {
                tl.getTradeFromPending(tl.getPendingTradesSize() - 1).addPlayerToTrade(t.getPlayer(i));
                tl.addTeamTwo(t);
                playerInTeam = true;
                break;
            }
        }
        if (!playerInTeam) {
            System.out.println("Player not found.");
        }
        processSecondTeam(t, tl);
    }

    // Requires: 0 <= i < t.getTeamSize()
    // Effects: print the information of the player.
    public void printPlayerInformation(Team t, int i) {
        System.out.println("Team: " + t.getPlayer(i).getTeam());
        System.out.println("Name: " + t.getPlayer(i).getName());
        System.out.println("Position: " + t.getPlayer(i).getPosition());
        System.out.println("Age: " + t.getPlayer(i).getAge());
        System.out.println("Height: " + t.getPlayer(i).getHeight() + "cm");
        System.out.println("Weight: " + t.getPlayer(i).getWeight() + "kg");
        System.out.println("Salary: " + t.getPlayer(i).getSalary() + " million dollars");
    }
}