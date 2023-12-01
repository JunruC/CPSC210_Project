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

    // Modifies: this
    // Effects: add a trade to the pending trade list.
    public void addPendingTrade(Trade t) {
        pendingTrades.add(t);
        EventLog.getInstance().logEvent(new Event("A trade is added to the pending trade list."));
    }

    // Modifies: this
    // Effects: remove all trades in pending trade list.
    public void clearPendingTrades() {
        pendingTrades.clear();
        EventLog.getInstance().logEvent(new Event("All trades are removed from the pending trade list."));
    }

    // Effects: print the information of pending trades.
    public String printPendingTrades() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < getPendingTrades().size(); j++) {
            sb.append("Players in trade No." + (j + 1) + ": ").append("\n");
            for (Player p : getPendingTrades().get(j).getTradedPlayers()) {
                sb.append("    " + p.getName() + " from " + p.getTeam()).append("\n");
            }
        }
        EventLog.getInstance().logEvent(new Event("All trades in the pending trade list are viewed."));
        return sb.toString();
    }

    public List<Trade> getPendingTrades() {
        return this.pendingTrades;
    }

    // Modifies: this
    // Effects: add a trade to the completed trade list.
    public void addCompletedTrade(Trade t) {
        completedTrades.add(t);
    }

    public List<Trade> getCompletedTrades() {
        return this.completedTrades;
    }

    // Modifies: this
    // Effects: add a team to the first side pending team list.
    public void addPendingTeamOne(Team t) {
        pendingTeamOne.add(t);
    }

    // Modifies: this
    // Effects: remove all teams in first side pending team list.
    public void clearPendingTeamOne() {
        pendingTeamOne.clear();
    }

    public List<Team> getPendingTeamOne() {
        return this.pendingTeamOne;
    }

    // Modifies: this
    // Effects: add a team to the second side pending team list.
    public void addPendingTeamTwo(Team t) {
        pendingTeamTwo.add(t);
    }

    // Modifies: this
    // Effects: remove all teams in second side pending team list.
    public void clearPendingTeamTwo() {
        pendingTeamTwo.clear();
    }

    public List<Team> getPendingTeamTwo() {
        return this.pendingTeamTwo;
    }

    // Modifies: this
    // Effects: add a team to the first side completed team list.
    public void addCompletedTeamOne(Team t) {
        completedTeamOne.add(t);
    }

    public List<Team> getCompletedTeamOne() {
        return this.completedTeamOne;
    }

    // Modifies: this
    // Effects: add a team to the second side completed team list.
    public void addCompletedTeamTwo(Team t) {
        completedTeamTwo.add(t);
    }

    public List<Team> getCompletedTeamTwo() {
        return this.completedTeamTwo;
    }

    // Modifies: this
    // Effects: add success or failure of the trade to the trade result list.
    public void addSuccess(Boolean result) {
        success.add(result);
    }

    public List<Boolean> getSuccess() {
        return this.success;
    }
}
