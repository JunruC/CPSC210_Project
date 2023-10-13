package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of trades with a list of pending trades, a list of completed trades, a list of first side teams in
// the trades, and a list of second side teams in the trades.
public class TradeList {
    private List<Trade> pendingTrades;    // a list of pending trades.
    private List<Trade> completedTrades;  // a list of completed trades.
    private List<Team> teamOne;           // a list of first side teams in the trades.
    private List<Team> teamTwo;           // a list of second side teams in the trades.

    // Effects: construct a list of trades with an empty list of pending trades, an empty list of completed trades,
    //          an empty list of first side teams in the trades, and an empty list of second side teams in the trades.
    public TradeList() {
        this.pendingTrades = new ArrayList<>();
        this.completedTrades = new ArrayList<>();
        this.teamOne = new ArrayList<>();
        this.teamTwo = new ArrayList<>();
    }

    // Modifies: this
    // Effects: add trade t to the list of pending trades.
    public void addTradeToPending(Trade t) {
        this.pendingTrades.add(t);
    }

    // Modifies: this
    // Effects: add trade t to the list of completed trades.
    public void addTradeToCompleted(Trade t) {
        this.completedTrades.add(t);
    }

    // Modifies: this
    // Effects: change the list of pending trades to an empty list.
    public void clearPendingTrades() {
        this.pendingTrades = new ArrayList<>();
    }

    // Modifies: this
    // Effects: add team t1 to the list of first side teams in the trades.
    public void addTeamOne(Team t1) {
        this.teamOne.add(t1);
    }

    // Modifies: this
    // Effects: add team t2 to the list of second side teams in the trades.
    public void addTeamTwo(Team t2) {
        this.teamTwo.add(t2);
    }

    // Requires: 0 <= i < getPendingTradesSize() + getCompletedTradesSize()
    // Effects: return the ith team in the list of first side teams in the trades.
    public Team getTeamOne(int i) {
        return this.teamOne.get(i);
    }

    // Requires: 0 <= i < getPendingTradesSize() + getCompletedTradesSize()
    // Effects: return the ith team in the list of second side teams in the trades.
    public Team getTeamTwo(int i) {
        return this.teamTwo.get(i);
    }

    // Requires: 0 <= i < getPendingTradesSize()
    // Effects: return the ith trade in the list of pending trades.
    public Trade getTradeFromPending(int i) {
        return this.pendingTrades.get(i);
    }

    // Requires: 0 <= i < getCompletedTradesSize()
    // Effects: return the ith trade in the list of completed trades.
    public Trade getTradeFromCompleted(int i) {
        return this.completedTrades.get(i);
    }

    // Effects: return the size of the list of pending trades.
    public int getPendingTradesSize() {
        return this.pendingTrades.size();
    }

    // Effects: return the size of the list of completed trades.
    public int getCompletedTradesSize() {
        return this.completedTrades.size();
    }

    public List<Trade> getPendingTrades() {
        return pendingTrades;
    }

    public List<Trade> getCompletedTrades() {
        return completedTrades;
    }

    public List<Team> getAllTeamOne() {
        return teamOne;
    }

    public List<Team> getAllTeamTwo() {
        return teamTwo;
    }
}
