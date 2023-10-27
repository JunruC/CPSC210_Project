package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of trades with a list of pending trades, a list of completed trades, a list of first side teams in
// the trades, and a list of second side teams in the trades.
public class TradeList {
    private List<Trade> pendingTrades;    // a list of pending trades.
    private List<Trade> completedTrades;  // a list of completed trades.
    private List<Team> pendingTeamOne;    // a list of first side teams in the pending trades.
    private List<Team> pendingTeamTwo;    // a list of second side teams in the pending trades.
    private List<Team> completedTeamOne;  // a list of first side teams in the completed trades.
    private List<Team> completedTeamTwo;  // a list of second side teams in the completed trades.
    private List<Boolean> success;        // a list of successes or failures of the trades.

    // Effects: construct a list of trades with an empty list of pending trades, an empty list of completed trades,
    //          an empty list of first side teams in the pending trades, an empty list of second side teams in the
    //          pending trades, an empty list of first side teams in the completed trades, an empty list of second side
    //          teams in the completed trades, and an empty list of successes or failures of the trades.
    public TradeList() {
        this.pendingTrades = new ArrayList<>();
        this.completedTrades = new ArrayList<>();
        this.pendingTeamOne = new ArrayList<>();
        this.pendingTeamTwo = new ArrayList<>();
        this.completedTeamOne = new ArrayList<>();
        this.completedTeamTwo = new ArrayList<>();
        this.success = new ArrayList<>();
    }

    public List<Trade> getPendingTrades() {
        return this.pendingTrades;
    }

    public List<Trade> getCompletedTrades() {
        return this.completedTrades;
    }

    public List<Team> getPendingTeamOne() {
        return this.pendingTeamOne;
    }

    public List<Team> getPendingTeamTwo() {
        return this.pendingTeamTwo;
    }

    public List<Team> getCompletedTeamOne() {
        return this.completedTeamOne;
    }

    public List<Team> getCompletedTeamTwo() {
        return this.completedTeamTwo;
    }

    public List<Boolean> getSuccess() {
        return this.success;
    }
}
