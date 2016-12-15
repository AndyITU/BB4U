package Model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class ShowTest {
    private Show show;
    @Before
    public void setUp() throws Exception {
        show = new Show(2,3,"Something", LocalDateTime.of(2016,10,4,2,5), LocalTime.of(5,3));
    }
    @Test
    public void getId() throws Exception {
        assertEquals(2, show.getId());
    }
    @Test
    public void getAud_id() throws Exception {
        assertEquals(3,show.getAud_id());
    }

    @Test
    public void getMovie() throws Exception {
        assertEquals("Something", show.getMovie());
    }

    @Test
    public void getDate() throws Exception {
        assertEquals(LocalDateTime.of(2016,10,4,2,5),show.getDate());
    }

    @Test
    public void getDuration() throws Exception {
        assertEquals(LocalTime.of(5,3),show.getDuration());
    }

}
