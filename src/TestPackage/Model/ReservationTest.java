package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by arha on 12/15/2016.
 */
public class ReservationTest {
    Reservation reservation;
    SeatModel[] seats = {new SeatModel(5,3),new SeatModel(2,6)};

    @Before
    public void setUp() throws Exception {
        reservation = new Reservation(2,6,seats,3,"James","12312312");
    }

    @Test
    public void getId() throws Exception {
        assertEquals(2,reservation.getId());
    }

    @Test
    public void getShow_id() throws Exception {
        assertEquals(6,reservation.getShow_id());
    }

    @Test
    public void getAud_id() throws Exception {
        assertEquals(3,reservation.getAud_id());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("James",reservation.getName());
    }

    @Test
    public void getContact_info() throws Exception {
        assertEquals("12312312",reservation.getContact_info());
    }

    @Test
    public void getSeats() throws Exception {
        assertArrayEquals(seats, reservation.getSeats());
    }

}