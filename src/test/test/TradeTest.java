package test;

import model.Player;
import model.Team;
import model.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TradeTest {
    private Trade t;
    private Player p1;
    private Player p2;

    @BeforeEach
    void runBefore() {
        t = new Trade();
        p1 = new Player("Denver Nuggets","Nikola Jokic","C",
                28,211,128.8,47.607);
        p2 = new Player("Atlanta Hawks","Trae Young","PG",
                25,185,74.4,40.064);
    }

    @Test
    void testConstructor() {
        assertEquals(0, t.getTradedPlayers().size());
    }

    @Test
    void testConductTradeOnePlayer() {
        Team t1 = new Team("Denver Nuggets");
        Team t2 = new Team("Atlanta Hawks");
        t.getTradedPlayers().add(p1);
        t.conductTrade(t1,t2);
        assertEquals(0, t1.getPlayers().size());
        assertEquals(1, t2.getPlayers().size());
        assertEquals(p1, t2.getPlayers().get(0));
        assertTrue(t.conductTrade(t1,t2));
    }

    @Test
    void testConductTradeTwoPlayersTwoTeams() {
        Team t1 = new Team("Denver Nuggets");
        Team t2 = new Team("Atlanta Hawks");
        t.getTradedPlayers().add(p1);
        t.getTradedPlayers().add(p2);
        t.conductTrade(t1,t2);
        assertEquals(1, t1.getPlayers().size());
        assertEquals(1, t2.getPlayers().size());
        assertEquals(p2, t1.getPlayers().get(0));
        assertEquals(p1, t2.getPlayers().get(0));
        assertTrue(t.conductTrade(t1,t2));
    }

    @Test
    void testConductTradeMoreThanTwoPlayersTwoTeams() {
        Player p3 = new Player("Atlanta Hawks","Onyeka Okongwu","PF",
                22,206,108.9,8.109);
        Team t1 = new Team("Denver Nuggets");
        Team t2 = new Team("Atlanta Hawks");
        t.getTradedPlayers().add(p1);
        t.getTradedPlayers().add(p2);
        t.getTradedPlayers().add(p3);
        t.conductTrade(t1,t2);
        assertEquals(2, t1.getPlayers().size());
        assertEquals(1, t2.getPlayers().size());
        assertEquals(p2, t1.getPlayers().get(0));
        assertEquals(p3, t1.getPlayers().get(1));
        assertEquals(p1, t2.getPlayers().get(0));
        assertTrue(t.conductTrade(t1,t2));
    }

    @Test
    void testConductTradeFail() {
        Player p3 = new Player("Golden State Warriors","Klay Thompson","SG",
                33,198,99.8,43.219);
        Team t1 = new Team("Denver Nuggets");
        Team t2 = new Team("Atlanta Hawks");
        t.getTradedPlayers().add(p1);
        t.getTradedPlayers().add(p3);
        t.conductTrade(t1,t2);
        assertFalse(t.conductTrade(t1,t2));
    }

    @Test
    void testAddPlayerToTradeOnce() {
        t.addPlayerToTrade(p1);
        assertEquals(1,t.getTradedPlayers().size());
        assertEquals(p1,t.getTradedPlayers().get(0));
    }

    @Test
    void testAddPlayerToTradeMultipleTimes() {
        t.addPlayerToTrade(p1);
        t.addPlayerToTrade(p2);
        assertEquals(2,t.getTradedPlayers().size());
        assertEquals(p1,t.getTradedPlayers().get(0));
        assertEquals(p2,t.getTradedPlayers().get(1));
    }
}
