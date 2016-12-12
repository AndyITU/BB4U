package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Write a description of class BookingFrame here.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class ButtonPanel extends JPanel
{
    private final JButton searchButton;
    private final JButton reservationButton;
    private final JButton bookingButton;

    ButtonPanel() {
        setLayout(new GridLayout(0,3));
        setPreferredSize(new Dimension(1000,100));
        setVisible(true);
        setMaximumSize(new Dimension(1000,250));
//asd
        reservationButton = new JButton("Reservation View");
        bookingButton = new JButton("Booking View");
        searchButton = new JButton("Search View");
        add(reservationButton);
        add(bookingButton);
        add(searchButton);
        
    }
    public JButton getReservationButton() {
        return reservationButton;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
    public JButton getBookingViewButton() {
        return bookingButton;
    }
        
}
