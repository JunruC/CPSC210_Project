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

    // Requires: t1 and t2 are different, trade.getTradeSize() > 0, players in trade can only from 1 or 2 teams
    // Modifies: t1, t2
    // Effects: for the ith player in the list of traded players, if the team of this player is the same as t1, he will
    //          be added to t2 and be removed from t1; if the team of this player is the same as t2, he will be added to
    //          t1 and be removed from t2. Return true.
    //          If either of the above situation happens, the system will print "Trade completed!". Otherwise, the
    //          system will print "Trade failed.". Return false.
    public boolean conductTrade(Team t1, Team t2, Trade trade) {
        boolean successful = true;
        for (int i = 0; i < trade.getTradedPlayers().size(); i++) {
            if (trade.getTradedPlayers().get(i).getTeam().equals(t1.getTeamName())) {
                Player tradingPlayer = trade.getTradedPlayers().get(i);
                t2.getPlayers().add(tradingPlayer);
                t1.getPlayers().remove(tradingPlayer);
            } else if (trade.getTradedPlayers().get(i).getTeam().equals(t2.getTeamName())) {
                Player tradingPlayer = trade.getTradedPlayers().get(i);
                t1.getPlayers().add(tradingPlayer);
                t2.getPlayers().remove(tradingPlayer);
            } else {
                successful = false;
            }
        }
        if (successful) {
            return true;
        } else {
            return false;
        }
    }

    public List<Player> getTradedPlayers() {
        return this.tradedPlayers;
    }
}
