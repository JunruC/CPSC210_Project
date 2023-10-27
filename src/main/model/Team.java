package model;

import java.util.ArrayList;
import java.util.List;

// Represents a team with its name, a list of players in it, total salary of all players in it in millions US dollars,
// and a tax line for the team in millions US dollars to determine whether it has to pay luxury tax.
public class Team {
    private String teamName;                   // the name of the team.
    private List<Player> players;              // a list of players in the team.
    private double totalSalary;                // total salary of all the players in the team in millions US dollars.
    private static double TAX_LINE = 165.294;  // tax line for the team in millions US dollars to determine whether it
                                               // has to pay luxury tax.

    // Requires: name is not an empty string.
    // Effects: construct a team with a name, an empty list of players, and a total salary of 0.
    public Team(String teamName) {
        this.teamName = teamName;
        this.players = new ArrayList<>();
        this.totalSalary = 0;
    }

    // Modifies: this
    // Effects: add salaries of all players in the team together to get the total salary.
    public void calculateTotalSalary() {
        double salarySum = 0;
        for (Player p : this.players) {
            salarySum += p.getSalary();
        }
        this.totalSalary = salarySum;
    }

    // Effects: judge whether the total salary of the team is above the tax line.
    public boolean isSalaryAboveTaxLine() {
        return this.totalSalary > TAX_LINE;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public double getTotalSalary() {
        return this.totalSalary;
    }

    public double getTaxLine() {
        return this.TAX_LINE;
    }
}
