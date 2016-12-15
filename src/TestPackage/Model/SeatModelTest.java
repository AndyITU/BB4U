package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SeatModelTest {
    SeatModel seat;
    @Before
    public void setUp() throws Exception {
        seat = new SeatModel(10,5);
    }

    @Test
    public void getCol() throws Exception {
        assertEquals(10,seat.getCol());
    }

    @Test
    public void getRow() throws Exception {
        assertEquals(5, seat.getRow());
    }

}