package model;

import java.util.ArrayList;
import java.util.List;

// Represents a team with its name, a list of players in it, total salary of all players in it in millions US dollars,
// and a tax line for the team in millions US dollars to determine whether it has to pay luxury tax.
public class Team {
    private String name;                       // the name of the team.
    private List<Player> players;              // a list of players in the team.
    private double totalSalary;                // total salary of all the players in the team in millions US dollars.
    private static double TAX_LINE = 165.294;  // tax line for the team in millions US dollars to determine whether it
                                               // has to pay luxury tax.

    // Requires: name is not an empty string.
    // Effects: construct a team with a name, an empty list of players, and a total salary of 0.
    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
        this.totalSalary = 0;
    }

    // Requires: team of player p must be the same as name of team.
    // Modifies: this
    // Effects: add player p to the list of players in the team.
    public void addPlayer(Player p) {
        this.players.add(p);
    }

    // Modifies: this
    // Effects: remove player p from the list of players in the team.
    public void removePlayer(Player p) {
        this.players.remove(p);
    }

    // Requires: 0 <= i < getTeamSize()
    // Effects: return the ith player in the list of players in the team.
    public Player getPlayer(int i) {
        return players.get(i);
    }

    // Effects: return the number of players in the team.
    public int getTeamSize() {
        return this.players.size();
    }

    // Modifies: this
    // Effects: add salaries of all players in the team together to get the total salary.
    public void calculateTotalSalary() {
        double salarySum = 0;
        for (int i = 0; i < getTeamSize(); i++) {
            salarySum += getPlayer(i).getSalary();
        }
        this.totalSalary = salarySum;
    }

    // Effects: judge whether the total salary of the team is above the tax line.
    public boolean isSalaryAboveTaxLine() {
        return this.totalSalary > TAX_LINE;
    }

    public String getTeamName() {
        return this.name;
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
