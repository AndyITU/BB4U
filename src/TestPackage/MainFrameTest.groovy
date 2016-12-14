package TestPackage

import Controller.Booking
import View.MainFrame

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Created by arha on 12/14/2016.
 */
class MainFrameTest {

    MainFrame frame;
    @Before
    void setUp() {
        frame = new MainFrame(1,1, Booking.getReservedSeats(1));
    }
    @After
    void tearDown() {

    }
}
