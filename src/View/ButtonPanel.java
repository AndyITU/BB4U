package View;

import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class View.ButtonPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ButtonPanel extends JPanel
{
    private final JButton searchButton;
    private final JButton firstPage;
    private final JButton bookingButton;

    ButtonPanel() {
        setLayout(new GridLayout(0,3));
        setPreferredSize(new Dimension(1000,100));
        setVisible(true);
        setMaximumSize(new Dimension(1000,250));

        firstPage = new JButton("Not View");
        bookingButton = new JButton("Booking View");
        searchButton = new JButton("Search View");
        add(firstPage);
        add(bookingButton);
        add(searchButton);
        
    }

    public JButton getSearchButton() {
        return searchButton;
    }
    public JButton getBookingViewButton() {
        return bookingButton;
    }
        
}
