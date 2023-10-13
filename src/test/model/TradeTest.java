package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TradeTest {
    private Trade t;
    private Player p1;

    @BeforeEach
    void runBefore() {
        t = new Trade();
        p1 = new Player("Denver Nuggets","Nikola Jokic","C",
                28,211,128.8,47.607);
    }

    @Test
    void testConstructor() {
        assertEquals(0, t.getTradedPlayers().size());
    }

    @Test
    void testAddPlayerToTradeOnce() {
        t.addPlayerToTrade(p1);
        assertEquals(1, t.getTradedPlayers().size());
        assertEquals(p1, t.getTradedPlayers().get(0));
    }

    @Test
    void testAddPlayerToTradeMultipleTimes() {
        Player p2 = new Player("Denver Nuggets", "Jamal Murray", "PG",
                26, 193, 97.5, 33.833);
        t.addPlayerToTrade(p1);
        t.addPlayerToTrade(p2);
        assertEquals(2, t.getTradedPlayers().size());
        assertEquals(p1, t.getTradedPlayers().get(0));
        assertEquals(p2, t.getTradedPlayers().get(1));
    }

    @Test
    void testGetTradedPlayer() {
        Player p2 = new Player("Denver Nuggets", "Jamal Murray", "PG",
                26, 193, 97.5, 33.833);
        t.getTradedPlayers().add(p1);
        t.getTradedPlayers().add(p2);
        assertEquals(p1, t.getTradedPlayer(0));
        assertEquals(p2, t.getTradedPlayer(1));
    }

    @Test
    void testGetTradeSize() {
        Player p2 = new Player("Denver Nuggets", "Jamal Murray", "PG",
                26, 193, 97.5, 33.833);
        t.getTradedPlayers().add(p1);
        t.getTradedPlayers().add(p2);
        assertEquals(2, t.getTradeSize());
    }

    @Test
    void testConductTradeOnePlayer() {
        Team t1 = new Team("Denver Nuggets");
        Team t2 = new Team("Atlanta Hawks");
        t.getTradedPlayers().add(p1);
        t.conductTrade(t1,t2,t);
        assertEquals(0, t1.getPlayers().size());
        assertEquals(1, t2.getPlayers().size());
        assertEquals(p1, t2.getPlayers().get(0));
        assertEquals("Atlanta Hawks", p1.getTeam());
    }

    @Test
    void testConductTradeTwoPlayersTwoTeams() {
        Player p2 = new Player("Atlanta Hawks","Trae Young","PG",
                25,185,74.4,40.064);
        Team t1 = new Team("Denver Nuggets");
        Team t2 = new Team("Atlanta Hawks");
        t.getTradedPlayers().add(p1);
        t.getTradedPlayers().add(p2);
        t.conductTrade(t1,t2,t);
        assertEquals(1, t1.getPlayers().size());
        assertEquals(1, t2.getPlayers().size());
        assertEquals(p2, t1.getPlayers().get(0));
        assertEquals(p1, t2.getPlayers().get(0));
        assertEquals("Atlanta Hawks", p1.getTeam());
        assertEquals("Denver Nuggets", p2.getTeam());
    }

    @Test
    void testConductTradeMoreThanTwoPlayersTwoTeams() {
        Player p2 = new Player("Atlanta Hawks","Trae Young","PG",
                25,185,74.4,40.064);
        Player p3 = new Player("Atlanta Hawks","Onyeka Okongwu","PF",
                22,206,108.9,8.109);
        Team t1 = new Team("Denver Nuggets");
        Team t2 = new Team("Atlanta Hawks");
        t.getTradedPlayers().add(p1);
        t.getTradedPlayers().add(p2);
        t.getTradedPlayers().add(p3);
        t.conductTrade(t1,t2,t);
        assertEquals(2, t1.getPlayers().size());
        assertEquals(1, t2.getPlayers().size());
        assertEquals(p2, t1.getPlayers().get(0));
        assertEquals(p3, t1.getPlayers().get(1));
        assertEquals(p1, t2.getPlayers().get(0));
        assertEquals("Atlanta Hawks", p1.getTeam());
        assertEquals("Denver Nuggets", p2.getTeam());
        assertEquals("Denver Nuggets", p3.getTeam());
    }
}
