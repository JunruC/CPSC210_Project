package persistence;

import model.Player;
import model.Team;
import model.Trade;
import model.TradeList;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReadTest {
    private Read r;
    private Save s;
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private Player p5;
    private Player p6;
    private Player p7;
    private Player p8;
    private Trade tr1;
    private Trade tr2;
    private Trade tr3;
    private Team t1;
    private Team t2;
    private Team t3;
    private Team t4;
    private TradeList tl;

    @BeforeEach
    void runBefore() {
        r = new Read();
        s = new Save();

        p1 = new Player("Golden State Warriors","Stephen Curry","PG",35,188,83.9,51.916);
        p2 = new Player("Golden State Warriors","Draymond Green","PF",33,198,104.3,22.321);
        p3 = new Player("Golden State Warriors","Klay Thompson","SG",33,198,99.8,43.219);

        p4 = new Player("Los Angeles Lakers","Anthony Davis","PF",30,208,114.8,40.600);
        p5 = new Player("Los Angeles Lakers","LeBron James","SF",38,206,113.4,47.607);
        p6 = new Player("Los Angeles Lakers","Austin Reaves","SG",25,196,89.4,12.015);

        p7 = new Player("Miami Heat","Jimmy Butler","SF",34,201,104.3,45.184);
        p8 = new Player("Atlanta Hawks","Trae Young","PG",25,185,74.4,40.064);

        tr1 = new Trade();
        tr1.getTradedPlayers().add(p1);
        tr1.getTradedPlayers().add(p4);

        tr2 = new Trade();
        tr2.getTradedPlayers().add(p2);
        tr2.getTradedPlayers().add(p5);

        tr3 = new Trade();
        tr3.getTradedPlayers().add(p7);
        tr3.getTradedPlayers().add(p8);

        t1 = new Team("Golden State Warriors");
        t1.getPlayers().add(p1);
        t1.getPlayers().add(p2);
        t1.getPlayers().add(p3);

        t2 = new Team("Los Angeles Lakers");
        t2.getPlayers().add(p4);
        t2.getPlayers().add(p5);
        t2.getPlayers().add(p6);

        t3 = new Team("Golden State Warriors");
        t3.getPlayers().add(p1);
        t3.getPlayers().add(p3);
        t3.getPlayers().add(p5);

        t4 = new Team("Los Angeles Lakers");
        t4.getPlayers().add(p4);
        t4.getPlayers().add(p6);
        t4.getPlayers().add(p2);

        tl = new TradeList();
        tl.getPendingTrades().add(tr1);
        tl.getCompletedTrades().add(tr2);
        tl.getCompletedTrades().add(tr3);
        tl.getPendingTeamOne().add(t1);
        tl.getPendingTeamTwo().add(t2);
        tl.getCompletedTeamOne().add(t3);
        tl.getCompletedTeamOne().add(t1);
        tl.getCompletedTeamTwo().add(t4);
        tl.getCompletedTeamTwo().add(t2);
        tl.getSuccess().add(true);
        tl.getSuccess().add(false);
    }

    @Test
    void testReadPendingTradesFind() {
        try {
            s.savePendingTrades(tl, "PendingTradesFile.json");
            TradeList tradelist = new TradeList();
            r.readPendingTrades(tradelist, "PendingTradesFile.json");
            assertEquals(1, tradelist.getPendingTrades().size());
            assertEquals(2, tradelist.getPendingTrades().get(0).getTradedPlayers().size());
            assertEquals("Stephen Curry", tradelist.getPendingTrades().get(0).getTradedPlayers().get(0).getName());
            assertEquals("Anthony Davis", tradelist.getPendingTrades().get(0).getTradedPlayers().get(1).getName());
        } catch (IOException e) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    void testReadPendingTradesNotFind() {
        try {
            s.savePendingTrades(tl, "PendingTradesFile.json");
            TradeList tradelist = new TradeList();
            r.readPendingTrades(tradelist, "xxx.json");
            fail("Expected exception not thrown.");
        } catch (IOException e) {
        }
    }

    @Test
    void testReadCompletedTradesFind() {
        try {
            s.saveCompletedTrades(tl, "CompletedTradesFile.json");
            TradeList tradelist = new TradeList();
            r.readCompletedTrades(tradelist, "CompletedTradesFile.json");
            assertEquals(2, tradelist.getCompletedTrades().size());
            assertEquals(2, tradelist.getCompletedTrades().get(0).getTradedPlayers().size());
            assertEquals("Draymond Green",
                    tradelist.getCompletedTrades().get(0).getTradedPlayers().get(0).getName());
            assertEquals("LeBron James",
                    tradelist.getCompletedTrades().get(0).getTradedPlayers().get(1).getName());
            assertEquals(2, tradelist.getCompletedTrades().get(1).getTradedPlayers().size());
            assertEquals("Jimmy Butler",
                    tradelist.getCompletedTrades().get(1).getTradedPlayers().get(0).getName());
            assertEquals("Trae Young",
                    tradelist.getCompletedTrades().get(1).getTradedPlayers().get(1).getName());
        } catch (IOException e) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    void testReadCompletedTradesNotFind() {
        try {
            s.saveCompletedTrades(tl, "CompletedTradesFile.json");
            TradeList tradelist = new TradeList();
            r.readCompletedTrades(tradelist, "xxx.json");
            fail("Expected exception not thrown.");
        } catch (IOException e) {
        }
    }

    @Test
    void testReadPendingTeamOneFind() {
        try {
            s.savePendingTeamOne(tl, "PendingTeamOneFile.json");
            TradeList tradelist = new TradeList();
            r.readPendingTeamOne(tradelist, "PendingTeamOneFile.json");
            assertEquals(1, tradelist.getPendingTeamOne().size());
            assertEquals("Golden State Warriors", tradelist.getPendingTeamOne().get(0).getTeamName());
            assertEquals("Stephen Curry", tradelist.getPendingTeamOne().get(0).getPlayers().get(0).getName());
            assertEquals("Draymond Green", tradelist.getPendingTeamOne().get(0).getPlayers().get(1).getName());
            assertEquals("Klay Thompson", tradelist.getPendingTeamOne().get(0).getPlayers().get(2).getName());
        } catch (IOException e) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    void testReadPendingTeamOneNotFind() {
        try {
            s.savePendingTeamOne(tl, "PendingTeamOneFile.json");
            TradeList tradelist = new TradeList();
            r.readPendingTeamOne(tradelist, "xxx.json");
            fail("Expected exception not thrown.");
        } catch (IOException e) {
        }
    }

    @Test
    void testReadPendingTeamTwoFind() {
        try {
            s.savePendingTeamTwo(tl, "PendingTeamTwoFile.json");
            TradeList tradelist = new TradeList();
            r.readPendingTeamTwo(tradelist, "PendingTeamTwoFile.json");
            assertEquals(1, tradelist.getPendingTeamTwo().size());
            assertEquals("Los Angeles Lakers", tradelist.getPendingTeamTwo().get(0).getTeamName());
            assertEquals("Anthony Davis", tradelist.getPendingTeamTwo().get(0).getPlayers().get(0).getName());
            assertEquals("LeBron James", tradelist.getPendingTeamTwo().get(0).getPlayers().get(1).getName());
            assertEquals("Austin Reaves", tradelist.getPendingTeamTwo().get(0).getPlayers().get(2).getName());
        } catch (IOException e) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    void testReadPendingTeamTwoNotFind() {
        try {
            s.savePendingTeamTwo(tl, "PendingTeamTwoFile.json");
            TradeList tradelist = new TradeList();
            r.readPendingTeamTwo(tradelist, "xxx.json");
            fail("Expected exception not thrown.");
        } catch (IOException e) {
        }
    }

    @Test
    void testReadCompletedTeamOneFind() {
        try {
            s.saveCompletedTeamOne(tl, "CompletedTeamOneFile.json");
            TradeList tradelist = new TradeList();
            r.readCompletedTeamOne(tradelist, "CompletedTeamOneFile.json");
            assertEquals(2, tradelist.getCompletedTeamOne().size());
            assertEquals("Golden State Warriors", tradelist.getCompletedTeamOne().get(0).getTeamName());
            assertEquals("Stephen Curry", tradelist.getCompletedTeamOne().get(0).getPlayers().get(0).getName());
            assertEquals("Klay Thompson", tradelist.getCompletedTeamOne().get(0).getPlayers().get(1).getName());
            assertEquals("LeBron James", tradelist.getCompletedTeamOne().get(0).getPlayers().get(2).getName());
            assertEquals("Golden State Warriors", tradelist.getCompletedTeamOne().get(1).getTeamName());
            assertEquals("Stephen Curry", tradelist.getCompletedTeamOne().get(1).getPlayers().get(0).getName());
            assertEquals("Draymond Green", tradelist.getCompletedTeamOne().get(1).getPlayers().get(1).getName());
            assertEquals("Klay Thompson", tradelist.getCompletedTeamOne().get(1).getPlayers().get(2).getName());
        } catch (IOException e) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    void testReadCompletedTeamOneNotFind() {
        try {
            s.saveCompletedTeamOne(tl, "CompletedTeamOneFile.json");
            TradeList tradelist = new TradeList();
            r.readCompletedTeamOne(tradelist, "xxx.json");
            fail("Expected exception not thrown.");
        } catch (IOException e) {
        }
    }

    @Test
    void testReadCompletedTeamTwoFind() {
        try {
            s.saveCompletedTeamTwo(tl, "CompletedTeamTwoFile.json");
            TradeList tradelist = new TradeList();
            r.readCompletedTeamTwo(tradelist, "CompletedTeamTwoFile.json");
            assertEquals(2, tradelist.getCompletedTeamTwo().size());
            assertEquals("Los Angeles Lakers", tradelist.getCompletedTeamTwo().get(0).getTeamName());
            assertEquals("Anthony Davis", tradelist.getCompletedTeamTwo().get(0).getPlayers().get(0).getName());
            assertEquals("Austin Reaves", tradelist.getCompletedTeamTwo().get(0).getPlayers().get(1).getName());
            assertEquals("Draymond Green", tradelist.getCompletedTeamTwo().get(0).getPlayers().get(2).getName());
            assertEquals("Los Angeles Lakers", tradelist.getCompletedTeamTwo().get(1).getTeamName());
            assertEquals("Anthony Davis", tradelist.getCompletedTeamTwo().get(1).getPlayers().get(0).getName());
            assertEquals("LeBron James", tradelist.getCompletedTeamTwo().get(1).getPlayers().get(1).getName());
            assertEquals("Austin Reaves", tradelist.getCompletedTeamTwo().get(1).getPlayers().get(2).getName());
        } catch (IOException e) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    void testReadCompletedTeamTwoNotFind() {
        try {
            s.saveCompletedTeamTwo(tl, "CompletedTeamTwoFile.json");
            TradeList tradelist = new TradeList();
            r.readCompletedTeamTwo(tradelist, "xxx.json");
            fail("Expected exception not thrown.");
        } catch (IOException e) {
        }
    }

    @Test
    void testReadSuccessFind() {
        try {
            s.saveSuccess(tl, "SuccessFile.json");
            TradeList tradelist = new TradeList();
            r.readSuccess(tradelist, "SuccessFile.json");
            assertEquals(2, tl.getSuccess().size());
            assertEquals(true, tl.getSuccess().get(0));
            assertEquals(false, tl.getSuccess().get(1));
        } catch (IOException e) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    void testReadSuccessNotFind() {
        try {
            s.saveSuccess(tl, "SuccessFile.json");
            TradeList tradelist = new TradeList();
            r.readSuccess(tradelist, "xxx.json");
            fail("Expected exception not thrown.");
        } catch (IOException e) {
        }
    }

    @Test
    void testReadPlayerData() {
        JSONObject savePlayerData = new JSONObject();
        s.savePlayerData(savePlayerData, p1);
        Player p = r.readPlayerData(savePlayerData);
        assertEquals("Golden State Warriors", p.getTeam());
        assertEquals("Stephen Curry", p.getName());
        assertEquals("PG", p.getPosition());
        assertEquals(35, p.getAge());
        assertEquals(188, p.getHeight());
        assertEquals(83.9, p.getWeight());
        assertEquals(51.916, p.getSalary());
    }
}
