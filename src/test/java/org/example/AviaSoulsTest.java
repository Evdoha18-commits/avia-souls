package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class AviaSoulsTest {

    private AviaSouls aviaSouls;

    @BeforeEach
    void setUp() {
        aviaSouls = new AviaSouls();
        aviaSouls.add(new Ticket("SVO", "LED", 5000, 10, 12));
        aviaSouls.add(new Ticket("SVO", "LED", 3000, 8, 11));
        aviaSouls.add(new Ticket("SVO", "LED", 7000, 14, 15));
        aviaSouls.add(new Ticket("SVO", "AER", 6000, 9, 13));
        aviaSouls.add(new Ticket("SVO", "LED", 4000, 6, 9));
    }

    @Test
    void testCompareTo_CheaperTicketIsLess() {
        Ticket cheap = new Ticket("SVO", "LED", 3000, 10, 12);
        Ticket expensive = new Ticket("SVO", "LED", 7000, 10, 12);
        assertTrue(cheap.compareTo(expensive) < 0);
    }

    @Test
    void testSearch_ReturnsSortedByPrice() {
        Ticket[] result = aviaSouls.search("SVO", "LED");

        assertEquals(4, result.length);
        assertEquals(3000, result[0].getPrice());
        assertEquals(4000, result[1].getPrice());
        assertEquals(5000, result[2].getPrice());
        assertEquals(7000, result[3].getPrice());
    }

    @Test
    void testTicketTimeComparator_ShorterFlightIsLess() {
        Ticket shortFlight = new Ticket("SVO", "LED", 7000, 14, 15);
        Ticket longFlight = new Ticket("SVO", "LED", 3000, 8, 11);

        TicketTimeComparator comp = new TicketTimeComparator();
        assertTrue(comp.compare(shortFlight, longFlight) < 0);
    }

    @Test
    void testSearchAndSortBy_TimeComparator() {
        Ticket[] result = aviaSouls.searchAndSortBy("SVO", "LED", new TicketTimeComparator());

        assertEquals(4, result.length);
        assertEquals(1, result[0].getTimeTo() - result[0].getTimeFrom());
        assertEquals(2, result[1].getTimeTo() - result[1].getTimeFrom());
        assertEquals(3, result[2].getTimeTo() - result[2].getTimeFrom());
        assertEquals(3, result[3].getTimeTo() - result[3].getTimeFrom());
    }
}
