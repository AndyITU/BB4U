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
    JButton searchButton;
    JButton firstPage;
    JButton bookingButton;

    public ButtonPanel() {
        super();
        this.setLayout(new GridLayout(0,3));
        this.setPreferredSize(new Dimension(1000,100));
        this.setVisible(true);
        this.setMaximumSize(new Dimension(1000,250));
        
        firstPage = new JButton("Not View");
        bookingButton = new JButton("Booking View");
        searchButton = new JButton("Search View");
        this.add(firstPage);
        this.add(bookingButton);
        this.add(searchButton);
        
    }

    public JButton getSearchButton() {
        return searchButton;
    }
    public JButton getBookingView() {
        return bookingButton;
    }
        
}
