package test;

import model.Player;
import model.Team;
import model.Trade;
import model.TradeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TradeListTest {
    private TradeList tl;
    private Trade t1;
    private Trade t2;
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private Team tm1;
    private Team tm2;

    @BeforeEach
    void runBefore() {
        tl = new TradeList();
        t1 = new Trade();
        t2 = new Trade();
        p1 = new Player("Denver Nuggets","Nikola Jokic","C",
                28,211,128.8,47.607);
        p2 = new Player("Golden State Warriors","Klay Thompson","SG",
                33,198,99.8,43.219);
        p3 = new Player("Atlanta Hawks","Trae Young","PG",
                25,185,74.4,40.064);
        p4 = new Player("Washington Wizards","Jordan Poole","SG",
                24,193,88.0,27.955);
        tm1 = new Team("Denver Nuggets");
        tm2 = new Team("Golden State Warriors");
        t1.addPlayerToTrade(p1);
        t1.addPlayerToTrade(p2);
        t2.addPlayerToTrade(p3);
        t2.addPlayerToTrade(p4);
    }

    @Test
    void testConstructor() {
        assertEquals(0, tl.getPendingTrades().size());
        assertEquals(0, tl.getCompletedTrades().size());
        assertEquals(0, tl.getPendingTeamOne().size());
        assertEquals(0, tl.getPendingTeamTwo().size());
        assertEquals(0, tl.getCompletedTeamOne().size());
        assertEquals(0, tl.getCompletedTeamTwo().size());
        assertEquals(0, tl.getSuccess().size());
    }

    @Test
    void testAddPendingTradeOnce() {
        tl.addPendingTrade(t1);
        assertEquals(1,tl.getPendingTrades().size());
        assertEquals(t1,tl.getPendingTrades().get(0));
    }

    @Test
    void testAddPendingTradeMultipleTimes() {
        tl.addPendingTrade(t1);
        tl.addPendingTrade(t2);
        assertEquals(2,tl.getPendingTrades().size());
        assertEquals(t1,tl.getPendingTrades().get(0));
        assertEquals(t2,tl.getPendingTrades().get(1));
    }

    @Test
    void testClearPendingTrades() {
        tl.addPendingTrade(t1);
        tl.addPendingTrade(t2);
        tl.clearPendingTrades();
        assertEquals(0,tl.getPendingTrades().size());
    }

    @Test
    void testAddCompletedTradeOnce() {
        tl.addCompletedTrade(t1);
        assertEquals(1,tl.getCompletedTrades().size());
        assertEquals(t1,tl.getCompletedTrades().get(0));
    }

    @Test
    void testAddCompletedTradeMultipleTimes() {
        tl.addCompletedTrade(t1);
        tl.addCompletedTrade(t2);
        assertEquals(2,tl.getCompletedTrades().size());
        assertEquals(t1,tl.getCompletedTrades().get(0));
        assertEquals(t2,tl.getCompletedTrades().get(1));
    }

    @Test
    void testAddPendingTeamOneOnce() {
        tl.addPendingTeamOne(tm1);
        assertEquals(1,tl.getPendingTeamOne().size());
        assertEquals(tm1,tl.getPendingTeamOne().get(0));
    }

    @Test
    void testAddPendingTeamOneMultipleTimes() {
        tl.addPendingTeamOne(tm1);
        tl.addPendingTeamOne(tm2);
        assertEquals(2,tl.getPendingTeamOne().size());
        assertEquals(tm1,tl.getPendingTeamOne().get(0));
        assertEquals(tm2,tl.getPendingTeamOne().get(1));
    }

    @Test
    void testClearPendingTeamOne() {
        tl.addPendingTeamOne(tm1);
        tl.addPendingTeamOne(tm2);
        tl.clearPendingTeamOne();
        assertEquals(0,tl.getPendingTeamOne().size());
    }

    @Test
    void testAddPendingTeamTwoOnce() {
        tl.addPendingTeamTwo(tm1);
        assertEquals(1,tl.getPendingTeamTwo().size());
        assertEquals(tm1,tl.getPendingTeamTwo().get(0));
    }

    @Test
    void testAddPendingTeamTwoMultipleTimes() {
        tl.addPendingTeamTwo(tm1);
        tl.addPendingTeamTwo(tm2);
        assertEquals(2,tl.getPendingTeamTwo().size());
        assertEquals(tm1,tl.getPendingTeamTwo().get(0));
        assertEquals(tm2,tl.getPendingTeamTwo().get(1));
    }

    @Test
    void testClearPendingTeamTwo() {
        tl.addPendingTeamTwo(tm1);
        tl.addPendingTeamTwo(tm2);
        tl.clearPendingTeamTwo();
        assertEquals(0,tl.getPendingTeamTwo().size());
    }

    @Test
    void testAddCompletedTeamOneOnce() {
        tl.addCompletedTeamOne(tm1);
        assertEquals(1,tl.getCompletedTeamOne().size());
        assertEquals(tm1,tl.getCompletedTeamOne().get(0));
    }

    @Test
    void testAddCompletedTeamOneMultipleTimes() {
        tl.addCompletedTeamOne(tm1);
        tl.addCompletedTeamOne(tm2);
        assertEquals(2, tl.getCompletedTeamOne().size());
        assertEquals(tm1, tl.getCompletedTeamOne().get(0));
        assertEquals(tm2, tl.getCompletedTeamOne().get(1));
    }

    @Test
    void testAddCompletedTeamTwoOnce() {
        tl.addCompletedTeamTwo(tm1);
        assertEquals(1,tl.getCompletedTeamTwo().size());
        assertEquals(tm1,tl.getCompletedTeamTwo().get(0));
    }

    @Test
    void testAddCompletedTeamTwoMultipleTimes() {
        tl.addCompletedTeamTwo(tm1);
        tl.addCompletedTeamTwo(tm2);
        assertEquals(2, tl.getCompletedTeamTwo().size());
        assertEquals(tm1, tl.getCompletedTeamTwo().get(0));
        assertEquals(tm2, tl.getCompletedTeamTwo().get(1));
    }

    @Test
    void testAddSuccessTrue() {
        tl.addSuccess(true);
        assertEquals(1,tl.getSuccess().size());
        assertTrue(tl.getSuccess().get(0));
    }

    @Test
    void testAddSuccessFalse() {
        tl.addSuccess(false);
        assertEquals(1,tl.getSuccess().size());
        assertFalse(tl.getSuccess().get(0));
    }

    @Test
    void testAddSuccessBoth() {
        tl.addSuccess(true);
        tl.addSuccess(false);
        assertEquals(2,tl.getSuccess().size());
        assertTrue(tl.getSuccess().get(0));
        assertFalse(tl.getSuccess().get(1));
    }

    @Test
    void testPrintPendingTradesOnce() {
        tl.addPendingTrade(t1);
        assertEquals("Players in trade No.1: \n" +
                "    Nikola Jokic from Denver Nuggets\n" +
                "    Klay Thompson from Golden State Warriors\n", tl.printPendingTrades());
    }

    @Test
    void testPrintPendingTradesMultipleTimes() {
        tl.addPendingTrade(t1);
        tl.addPendingTrade(t2);
        assertEquals("Players in trade No.1: \n" +
                "    Nikola Jokic from Denver Nuggets\n" +
                "    Klay Thompson from Golden State Warriors\n" +
                "Players in trade No.2: \n" +
                "    Trae Young from Atlanta Hawks\n" +
                "    Jordan Poole from Washington Wizards\n", tl.printPendingTrades());
    }
}
