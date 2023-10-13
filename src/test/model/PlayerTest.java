package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player p;

    @BeforeEach
    void runBefore() {
        p = new Player("Golden State Warriors","Klay Thompson","SG",
                33,198,99.8,43.219);
    }

    @Test
    void testConstructor() {
        assertEquals("Golden State Warriors", p.getTeam());
        assertEquals("Klay Thompson", p.getName());
        assertEquals("SG", p.getPosition());
        assertEquals(33, p.getAge());
        assertEquals(198, p.getHeight());
        assertEquals(99.8, p.getWeight());
        assertEquals(43.219, p.getSalary());
    }

    @Test
    void testChangeTeam() {
        p.changeTeam("Miami Heat");
        assertEquals("Miami Heat", p.getTeam());
    }
}