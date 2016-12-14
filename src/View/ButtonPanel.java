package View;

import javax.swing.*;
import java.awt.*;

/**
 * ButtonPanel is an extension of JPanel. It is the main way of traveling between panels.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class ButtonPanel extends JPanel
{
    private final JButton searchButton;
    private final JButton reservationButton;
    private final JButton bookingButton;

    /**
     * The constructor of ButtonPanel create and adds three JButtons to itself,
     * used for travelling between the three different views.
     */
    ButtonPanel() {
        setLayout(new GridLayout(0,3));
        setPreferredSize(new Dimension(1000,100));
        setVisible(true);
        setMaximumSize(new Dimension(1000,250));
        reservationButton = new JButton("Reservation View");
        bookingButton = new JButton("Booking View");
        searchButton = new JButton("Search View");
        add(reservationButton);
        add(bookingButton);
        add(searchButton);
        
    }
    /**
     * This method returns an object of the type JButton whenever it is called.
     * @return reservationButton that is currently exist in ButtonPanel.
     */
    public JButton getReservationButton() {
        return reservationButton;
    }
    /**
     * This method returns an object of the type JButton whenever it is called.
     * @return searchButton that is currently in the ButtonPanel
     */
    public JButton getSearchButton() {
        return searchButton;
    }
    /**
     * This method returns an object of the type JButton whenever it is called.
     * @return bookingButton that is currently in the ButtonPanel
     */
    public JButton getBookingViewButton() {
        return bookingButton;
    }
        
}
