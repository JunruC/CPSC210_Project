package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TradeListTest {
    private TradeList tl;
    private Trade trade1;

    @BeforeEach
    void runBefore() {
        tl = new TradeList();
        Player p1 = new Player("Denver Nuggets", "Nikola Jokic", "C",
                28, 211, 128.8, 47.607);
        Player p2 = new Player("Atlanta Hawks","Trae Young","PG",
                25,185,74.4,40.064);
        trade1 = new Trade();
        trade1.getTradedPlayers().add(p1);
        trade1.getTradedPlayers().add(p2);
    }

    @Test
    void testConstructor() {
        assertEquals(0, tl.getPendingTrades().size());
        assertEquals(0, tl.getCompletedTrades().size());
        assertEquals(0, tl.getAllTeamOne().size());
        assertEquals(0, tl.getAllTeamTwo().size());
    }

    @Test
    void testAddTradeToPendingOnce() {
        tl.addTradeToPending(trade1);
        assertEquals(1, tl.getPendingTrades().size());
        assertEquals(trade1, tl.getPendingTrades().get(0));
    }

    @Test
    void testAddTradeToPendingMultipleTimes() {
        Player p3 = new Player("Golden State Warriors","Klay Thompson","SG",
                33,198,99.8,43.219);
        Player p4 = new Player("Minnesota Timberwolves","Kyle Anderson","SF",
                30,206,104.3,9.220);
        Trade trade2 = new Trade();
        trade2.getTradedPlayers().add(p3);
        trade2.getTradedPlayers().add(p4);
        tl.addTradeToPending(trade1);
        tl.addTradeToPending(trade2);
        assertEquals(2, tl.getPendingTrades().size());
        assertEquals(trade1, tl.getPendingTrades().get(0));
        assertEquals(trade2, tl.getPendingTrades().get(1));
    }

    @Test
    void testAddTradeToCompletedOnce() {
        tl.addTradeToCompleted(trade1);
        assertEquals(1, tl.getCompletedTrades().size());
        assertEquals(trade1, tl.getCompletedTrades().get(0));
    }

    @Test
    void testAddTradeToCompletedMultipleTimes() {
        Player p3 = new Player("Golden State Warriors","Klay Thompson","SG",
                33,198,99.8,43.219);
        Player p4 = new Player("Minnesota Timberwolves","Kyle Anderson","SF",
                30,206,104.3,9.220);
        Trade trade2 = new Trade();
        trade2.getTradedPlayers().add(p3);
        trade2.getTradedPlayers().add(p4);
        tl.addTradeToCompleted(trade1);
        tl.addTradeToCompleted(trade2);
        assertEquals(2, tl.getCompletedTrades().size());
        assertEquals(trade1, tl.getCompletedTrades().get(0));
        assertEquals(trade2, tl.getCompletedTrades().get(1));
    }

    @Test
    void testClearPendingTradesEmpty() {
        tl.clearPendingTrades();
        assertEquals(0, tl.getPendingTrades().size());
    }

    @Test
    void testClearPendingTradesNotEmpty() {
        Player p3 = new Player("Golden State Warriors","Klay Thompson","SG",
                33,198,99.8,43.219);
        Player p4 = new Player("Minnesota Timberwolves","Kyle Anderson","SF",
                30,206,104.3,9.220);
        Trade trade2 = new Trade();
        trade2.getTradedPlayers().add(p3);
        trade2.getTradedPlayers().add(p4);
        tl.addTradeToCompleted(trade1);
        tl.addTradeToCompleted(trade2);
        tl.clearPendingTrades();
        assertEquals(0, tl.getPendingTrades().size());
    }

    @Test
    void testAddTeamOneOnce() {
        Team t1 = new Team("Atlanta Hawks");
        tl.addTeamOne(t1);
        assertEquals(1, tl.getAllTeamOne().size());
        assertEquals(t1, tl.getAllTeamOne().get(0));
    }

    @Test
    void testAddTeamOneMultipleTimes() {
        Team t1 = new Team("Atlanta Hawks");
        Team t2 = new Team("Golden State Warriors");
        tl.addTeamOne(t1);
        tl.addTeamOne(t2);
        assertEquals(2, tl.getAllTeamOne().size());
        assertEquals(t1, tl.getAllTeamOne().get(0));
        assertEquals(t2, tl.getAllTeamOne().get(1));
    }

    @Test
    void testAddTeamTwoOnce() {
        Team t1 = new Team("Atlanta Hawks");
        tl.addTeamTwo(t1);
        assertEquals(1, tl.getAllTeamTwo().size());
        assertEquals(t1, tl.getAllTeamTwo().get(0));
    }

    @Test
    void testAddTeamTwoMultipleTimes() {
        Team t1 = new Team("Atlanta Hawks");
        Team t2 = new Team("Golden State Warriors");
        tl.addTeamTwo(t1);
        tl.addTeamTwo(t2);
        assertEquals(2, tl.getAllTeamTwo().size());
        assertEquals(t1, tl.getAllTeamTwo().get(0));
        assertEquals(t2, tl.getAllTeamTwo().get(1));
    }

    @Test
    void testGetTeamOne() {
        Team t1 = new Team("Atlanta Hawks");
        Team t2 = new Team("Golden State Warriors");
        tl.addTeamOne(t1);
        tl.addTeamOne(t2);
        assertEquals(t1, tl.getTeamOne(0));
        assertEquals(t2, tl.getTeamOne(1));
    }

    @Test
    void testGetTeamTwo() {
        Team t1 = new Team("Atlanta Hawks");
        Team t2 = new Team("Golden State Warriors");
        tl.addTeamTwo(t1);
        tl.addTeamTwo(t2);
        assertEquals(t1, tl.getTeamTwo(0));
        assertEquals(t2, tl.getTeamTwo(1));
    }

    @Test
    void testGetTradeFromPending() {
        Player p3 = new Player("Golden State Warriors","Klay Thompson","SG",
                33,198,99.8,43.219);
        Player p4 = new Player("Minnesota Timberwolves","Kyle Anderson","SF",
                30,206,104.3,9.220);
        Trade trade2 = new Trade();
        trade2.getTradedPlayers().add(p3);
        trade2.getTradedPlayers().add(p4);
        tl.getPendingTrades().add(trade1);
        tl.getPendingTrades().add(trade2);
        assertEquals(trade1, tl.getTradeFromPending(0));
        assertEquals(trade2, tl.getTradeFromPending(1));
    }

    @Test
    void testGetTradeFromCompleted() {
        Player p3 = new Player("Golden State Warriors","Klay Thompson","SG",
                33,198,99.8,43.219);
        Player p4 = new Player("Minnesota Timberwolves","Kyle Anderson","SF",
                30,206,104.3,9.220);
        Trade trade2 = new Trade();
        trade2.getTradedPlayers().add(p3);
        trade2.getTradedPlayers().add(p4);
        tl.getCompletedTrades().add(trade1);
        tl.getCompletedTrades().add(trade2);
        assertEquals(trade1, tl.getTradeFromCompleted(0));
        assertEquals(trade2, tl.getTradeFromCompleted(1));
    }

    @Test
    void testGetPendingTradesSize() {
        Player p3 = new Player("Golden State Warriors","Klay Thompson","SG",
                33,198,99.8,43.219);
        Player p4 = new Player("Minnesota Timberwolves","Kyle Anderson","SF",
                30,206,104.3,9.220);
        Trade trade2 = new Trade();
        trade2.getTradedPlayers().add(p3);
        trade2.getTradedPlayers().add(p4);
        tl.getPendingTrades().add(trade1);
        tl.getPendingTrades().add(trade2);
        assertEquals(2, tl.getPendingTradesSize());
    }

    @Test
    void testGetCompletedTradesSize() {
        Player p3 = new Player("Golden State Warriors","Klay Thompson","SG",
                33,198,99.8,43.219);
        Player p4 = new Player("Minnesota Timberwolves","Kyle Anderson","SF",
                30,206,104.3,9.220);
        Trade trade2 = new Trade();
        trade2.getTradedPlayers().add(p3);
        trade2.getTradedPlayers().add(p4);
        tl.getCompletedTrades().add(trade1);
        tl.getCompletedTrades().add(trade2);
        assertEquals(2, tl.getCompletedTradesSize());
    }
}
