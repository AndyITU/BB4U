package Model;

import org.junit.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mikkel on 14-12-2016.
 */
class SeatModelTest {
    SeatModel test;
    @Before
    public void setUp() {
        test = new SeatModel(5,3,true);
    }

    @After
    public void tearDown() {
        test = null;
    }

    @Test
    public void getCol() {
        assertEquals(5, test.getCol());
    }

    @Test
    public void getRow() {
        assertEquals(3, test.getRow());
    }

    @Test
    public void getIsBooked() {
        assertEquals(true,test.getIsBooked());
    }

}