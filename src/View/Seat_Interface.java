package View;

import java.awt.*;

/**
 * Created by Mikkel on 09-12-2016.
 */
public interface Seat_Interface {
    void makeSquare(Graphics g);
    void setModelBooked(Boolean b);
    boolean setBooked(Boolean b);
    void seatName(int k, int s, Graphics g);
}
