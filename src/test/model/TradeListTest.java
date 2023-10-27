package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TradeListTest {
    private TradeList tl;

    @BeforeEach
    void runBefore() {
        tl = new TradeList();
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
}
