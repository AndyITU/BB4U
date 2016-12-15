package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by arha on 12/15/2016.
 */
public class AuditoriumTest {
    Auditorium auditorium;
    @Before
    public void setup() throws Exception {
        auditorium = new Auditorium(2,10,20);
    }
    @Test
    public void getId() throws Exception {
        assertEquals(2,auditorium.getId());
    }

    @Test
    public void getRows() throws Exception {
        assertEquals(10,auditorium.getRows());
    }

    @Test
    public void getCols() throws Exception {
        assertEquals(20,auditorium.getCols());
    }

}