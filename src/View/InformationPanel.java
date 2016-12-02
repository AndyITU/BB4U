package View;

import Model.SeatModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Write a description of class View.ButtonPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class InformationPanel extends JPanel
{
    private SeatModel seat;
    private int bookCount;
    private SeatPanel panel;


    public InformationPanel(SeatPanel panel) {
        super();
        this.panel = panel;
        this.setLayout(new GridLayout(2,2));
        this.setPreferredSize(new Dimension(1000,50));
        this.setVisible(true);
        
        JButton button = new JButton("BOOK SELECTED");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookCount++;
                if (bookCount % 2 == 1)
                panel.startBook(true);
                else {
                    panel.startBook(false);
                }
                /**
                try {
                        // Set the seat to something plz.. Can't wait to test!
                        Booking.bookSeats(new Model.SeatModel[]{seat});
                    } catch(IllegalArgumentException | SQLException err) {
                        // Make a class for an error-message-window and use it here:
                        System.out.println(err);
                    }
                    */

                }
            });
        this.add(setFirst());
        this.add(setFirst());
        this.add(button);
        this.setBackground(Color.BLACK);
        this.add(new JButton("im here for fun"));
    }
    
    private JPanel setFirst() {
        JPanel panelOne = new JPanel();
        panelOne.setLayout(new GridLayout(3,2));
        int bifID = 3;
        
        JLabel theaterS = new JLabel("Film:");
        JLabel theaterLabel = new JLabel("Sal:");
        JLabel movieS = new JLabel("Biografsal nr:" + bifID + ".   " + "60/70 sæder ledige");
        JLabel movieLabel = new JLabel("WarZ with Brad Pitt");
        
        panelOne.add(theaterS);
        panelOne.add(movieLabel);
        panelOne.add(theaterLabel);
        panelOne.add(movieS);
        panelOne.add(new JLabel("Ledige sæder:"));
        panelOne.add(new JLabel("100/150"));
        return panelOne;
    }
}