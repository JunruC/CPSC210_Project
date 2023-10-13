package model;

import java.util.ArrayList;
import java.util.List;

// Represents a trade with a list of traded players.
public class Trade {
    private List<Player> tradedPlayers;   // a list of traded players.

    // Effects: construct a trade with an empty list of traded players.
    public Trade() {
        this.tradedPlayers = new ArrayList<>();
    }

    // Modifies: this
    // Effects: add player p to the list of traded players.
    public void addPlayerToTrade(Player p) {
        this.tradedPlayers.add(p);
    }

    // Requires: 0 <= i < getTradeSize()
    // Effects: return the ith player in this trade.
    public Player getTradedPlayer(int i) {
        return tradedPlayers.get(i);
    }

    // Effects: return the number of players in this trade.
    public int getTradeSize() {
        return this.tradedPlayers.size();
    }

    // Requires: t1 and t2 are different, trade.getTradeSize() > 0, players in trade can only from 1 or 2 teams
    // Modifies: t1, t2
    // Effects: for the ith player in the list of traded players, if the team of this player is the same as t1, he will
    //          be added to t2 and be removed from t1, and his team changes to the name of t2; if the team of this
    //          player is the same as t2, he will be added to t1 and be removed from t2, and his team changes to the
    //          name of t1. Return true.
    //          If either of the above situation happens, the system will print "Trade completed!". Otherwise, the
    //          system will print "Trade failed.". Return false.
    public boolean conductTrade(Team t1, Team t2, Trade trade) {
        boolean success = true;
        for (int i = 0; i < trade.getTradeSize(); i++) {
            if (trade.getTradedPlayer(i).getTeam().equals(t1.getTeamName())) {
                Player tradingPlayer = trade.getTradedPlayer(i);
                tradingPlayer.changeTeam(t2.getTeamName());
                t2.addPlayer(tradingPlayer);
                t1.removePlayer(tradingPlayer);
            } else if (trade.getTradedPlayer(i).getTeam().equals(t2.getTeamName())) {
                Player tradingPlayer = trade.getTradedPlayer(i);
                tradingPlayer.changeTeam(t1.getTeamName());
                t1.addPlayer(tradingPlayer);
                t2.removePlayer(tradingPlayer);
            } else {
                success = false;
            }
        }
        if (success) {
            System.out.print("Trade completed!");
            return true;
        } else {
            System.out.println("Trade failed.");
            return false;
        }
    }

    public List<Player> getTradedPlayers() {
        return this.tradedPlayers;
    }
}
