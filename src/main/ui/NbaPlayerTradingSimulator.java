package ui;

import model.Player;
import model.Team;
import model.Trade;
import model.TradeList;
import persistence.Read;
import persistence.Save;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// NBA Player Trading Simulator Application
public class NbaPlayerTradingSimulator {

    // Effects: run the NBA Player Trading Simulator Application.
    public NbaPlayerTradingSimulator() {
        TradeList tl = new TradeList();
        readData(tl);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            printOptions();
            int option = scanner.nextInt();
            if (option == 4) {
                saveData(tl);
                System.out.println("Quit successfully.");
                break;
            } else {
                if (option == 1) {
                    optionOne(tl);
                } else if (option == 2) {
                    optionTwo(tl);
                } else if (option == 3) {
                    optionThree(tl);
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
        Trade t = new Trade();
        tl.getPendingTrades().add(t);
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
            Trade t0 = new Trade();
            tl.getPendingTrades().add(t0);
            makeFirstTeam(tl);
            makeSecondTeam(tl);
        }
    }

    // Modifies: this
    // Effects: process option 2.
    public void optionTwo(TradeList tl) {
        if (tl.getPendingTrades().size() == 0) {
            System.out.println("There is no trade to be conducted now.");
        } else {
            for (int i = 0; i < tl.getPendingTrades().size(); i++) {
                processTrade(tl, i);
            }
            tl.getPendingTrades().clear();
            tl.getPendingTeamOne().clear();
            tl.getPendingTeamTwo().clear();
        }
    }

    // Requires: 0 <= i < tl.getPendingTrades().size()
    // Modifies: this
    // Effects: process the trade in pending trades.
    public void processTrade(TradeList tl, int i) {
        Trade tradeInProcess = tl.getPendingTrades().get(i);
        System.out.println("No." + (i + 1) + ": ");
        Team ct1 = new Team(tl.getPendingTeamOne().get(i).getTeamName());
        for (int j = 0; j < tl.getPendingTeamOne().get(i).getPlayers().size(); j++) {
            ct1.getPlayers().add(tl.getPendingTeamOne().get(i).getPlayers().get(j));
        }
        Team ct2 = new Team(tl.getPendingTeamTwo().get(i).getTeamName());
        for (int k = 0; k < tl.getPendingTeamTwo().get(i).getPlayers().size(); k++) {
            ct2.getPlayers().add(tl.getPendingTeamTwo().get(i).getPlayers().get(k));
        }
        Boolean success = tradeInProcess.conductTrade(tl.getPendingTeamOne().get(i),
                tl.getPendingTeamTwo().get(i), tradeInProcess);
        tl.getSuccess().add(success);
        System.out.println("");
        tl.getCompletedTrades().add(tradeInProcess);
        if (success) {
            tl.getCompletedTeamOne().add(tl.getPendingTeamOne().get(i));
            tl.getCompletedTeamTwo().add(tl.getPendingTeamTwo().get(i));
        } else {
            tl.getCompletedTeamOne().add(ct1);
            tl.getCompletedTeamTwo().add(ct2);
        }
    }

    // Effects: process option 3.
    public void optionThree(TradeList tl) {
        if (tl.getCompletedTrades().size() == 0) {
            System.out.println("No trade completed yet.");
        } else {
            for (int i = 0; i < tl.getCompletedTrades().size(); i++) {
                System.out.println("Trade " + (i + 1) + ": ");
                if (tl.getSuccess().get(i)) {
                    Trade trade = tl.getCompletedTrades().get(i);
                    Team t1 = tl.getCompletedTeamOne().get(i);
                    Team t2 = tl.getCompletedTeamTwo().get(i);
                    for (int j = 0; j < trade.getTradedPlayers().size(); j++) {
                        Player p = trade.getTradedPlayers().get(j);
                        if (p.getTeam().equals(t1.getTeamName())) {
                            System.out.println(p.getName() + " goes to " + t2.getTeamName());
                        } else {
                            System.out.println(p.getName() + " goes to " + t1.getTeamName());
                        }
                    }
                    salaryReport(t1, t2);
                } else {
                    System.out.println("This trade failed.");
                }
            }
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
    // Effects: make the first side team of the trade and then process the team.
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
                t1.getPlayers().add(p);
            }
            tl.getPendingTeamOne().add(t1);
            processFirstTeam(t1, tl);
        } catch (IOException e) {
            System.out.println("Team does not exist.");
            makeFirstTeam(tl);
        }
    }

    // Modifies: this
    // Effects: make the second side team of the trade and then process the team.
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
                t2.getPlayers().add(p);
            }
            tl.getPendingTeamTwo().add(t2);
            processSecondTeam(t2, tl);
        } catch (IOException e) {
            System.out.println("Team does not exist.");
            makeSecondTeam(tl);
        }
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
        for (int i = 0; i < t.getPlayers().size(); i++) {
            System.out.println(t.getPlayers().get(i).getName());
        }
        processFirstTeam(t, tl);
    }

    // Effects: process option 2 when process the first team.
    public void firstTeamOptionTwo(Team t, TradeList tl) {
        Scanner s2 = new Scanner(System.in);
        System.out.println("Enter the name of the player you want to view:");
        String name = s2.nextLine();
        boolean findPlayer = false;
        for (int i = 0; i < t.getPlayers().size(); i++) {
            if (name.equals(t.getPlayers().get(i).getName())) {
                printPlayerInformation(t, i);
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
        boolean playerInTeam = false;
        for (int i = 0; i < t.getPlayers().size(); i++) {
            if (player.equals(t.getPlayers().get(i).getName())) {
                Trade trade = tl.getPendingTrades().get(tl.getPendingTrades().size() - 1);
                trade.getTradedPlayers().add(t.getPlayers().get(i));
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
        for (int i = 0; i < t.getPlayers().size(); i++) {
            System.out.println(t.getPlayers().get(i).getName());
        }
        processSecondTeam(t, tl);
    }

    // Effects: process option 2 when process the second team.
    public void secondTeamOptionTwo(Team t, TradeList tl) {
        Scanner s2 = new Scanner(System.in);
        System.out.println("Enter the name of the player you want to view:");
        String name = s2.nextLine();
        boolean findPlayer = false;
        for (int i = 0; i < t.getPlayers().size(); i++) {
            if (name.equals(t.getPlayers().get(i).getName())) {
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
        System.out.println("Enter the name of the player you want to get from:");
        String player = s3.nextLine();
        boolean playerInTeam = false;
        for (int i = 0; i < t.getPlayers().size(); i++) {
            if (player.equals(t.getPlayers().get(i).getName())) {
                Trade trade = tl.getPendingTrades().get(tl.getPendingTrades().size() - 1);
                trade.getTradedPlayers().add(t.getPlayers().get(i));
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
        System.out.println("Team: " + t.getPlayers().get(i).getTeam());
        System.out.println("Name: " + t.getPlayers().get(i).getName());
        System.out.println("Position: " + t.getPlayers().get(i).getPosition());
        System.out.println("Age: " + t.getPlayers().get(i).getAge());
        System.out.println("Height: " + t.getPlayers().get(i).getHeight() + "cm");
        System.out.println("Weight: " + t.getPlayers().get(i).getWeight() + "kg");
        System.out.println("Salary: " + t.getPlayers().get(i).getSalary() + " million dollars");
    }

    // Effects: read the data in the json files.
    public void readData(TradeList tl) {
        Scanner scanner = new Scanner(System.in);
        askRead();
        int option = scanner.nextInt();
        if (option == 1) {
            readDataOptionOne(tl);
        } else if (option == 2) {
            readDataOptionTwo();
        }
    }

    // Effects: ask the users whether they want to reload the recent progress.
    public void askRead() {
        System.out.println("Do you want to reload the recent progress?");
        System.out.println("1. Yes");
        System.out.println("2. No");
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
            System.out.println("Unable to read the data.");
        }
    }

    // Effects: process option two when read data.
    public void readDataOptionTwo() {
        Save s = new Save();
        try {
            s.clearData("PendingTradesFile.json","CompletedTradesFile.json","PendingTeamOneFile.json",
                    "PendingTeamTwoFile.json","CompletedTeamOneFile.json","CompletedTeamTwoFile.json",
                    "SuccessFile.json");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to clear the data.");
        }
    }

    // Effects: save the data in the trade list.
    public void saveData(TradeList tl) {
        Scanner scanner = new Scanner(System.in);
        askSave();
        int option = scanner.nextInt();
        if (option == 1) {
            saveDataOptionOne(tl);
        }
    }

    // Effects: ask the users whether they want to save the current progress.
    public void askSave() {
        System.out.println("Do you want to save the current progress?");
        System.out.println("1. Yes");
        System.out.println("2. No");
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
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save the data.");
        }
    }
}