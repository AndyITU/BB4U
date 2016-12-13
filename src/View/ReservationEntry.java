package View;

import Controller.Booking;
import Controller.ViewController;
import Model.Reservation;
import Model.Show;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
/**
 * ReservationEntry is an extension of Button, it consists of all necessary information about a reservation,
 * and has an implementation of MouseListener.
 * It represents an already created reservation and its main focus is to aid in altering already existing reservations.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class ReservationEntry extends JButton implements MouseListener {

    private Reservation customer;
    private boolean isHighlighted;
    private int seats;
    private int audID;
    private String date;
    private String movieLabel;
    private String contactInfo;
    private String contactName;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd. MMMM - yyyy HH:mm", new Locale("da", "DK"));

    /**
     * ReservationEntry needs a Reservation to start creating a graphical outline of the reservation.
     * @param res the reservation to be displayed, and alterable.
     */
    public ReservationEntry(Reservation res) {
        setSize(new Dimension (1000, 70));
        setMaximumSize(getSize());
        setMinimumSize(new Dimension(1000, 10));
        addMouseListener(this);
        setContentAreaFilled(false);
        setVisible(true);
        customer = res;
        Show s = Booking.getShow(customer.getShow_id());
        seats = customer.getSeats().length;
        audID = customer.getAud_id();
        date = s.getDate().format(format);
        String movie = s.getMovie();
        if (movie.length() > 32) {
            movieLabel = movie.substring(0,32)+"...";
        }
        else {
            movieLabel = movie;
        }
        contactInfo = customer.getContact_info();
        contactName = customer.getName();
    }
    public void paint(Graphics g) {
        if (isHighlighted) {
            g.setColor(Color.black);
        }
        else {
            g.setColor(Color.darkGray);
        }
        newEntry(g);
    }
    public void mouseClicked(MouseEvent e) {
        String[] optionList = {
                "Edit",
                "Remove",
                "Cancel"
        };
        int answer = JOptionPane.showOptionDialog(null, "Would you like to edit, or remove?", "Reservation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionList, optionList[2]);
        ViewController.getInstance().sendAnswer(answer, customer);
    }
    public void mouseExited(MouseEvent e) {
        isHighlighted = false;
        repaint();
    }
    public void mouseEntered(MouseEvent e) {
        isHighlighted = true;
        repaint();
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void newEntry(Graphics g) {
        g.fillRect(getWidth()/15,getHeight()/15,getWidth()-getWidth()/15*2,getHeight()-getHeight()/15*2);
        g.setColor(Color.BLACK);
        g.drawRect(getWidth()/15,getHeight()/15,getWidth()-getWidth()/15*2,getHeight()-getHeight()/15*2);
        g.setColor(Color.WHITE);
        g.drawString("Name: " + contactName, getWidth()/10, (getHeight()/4)+5);
        g.drawString("Contact info: " + contactInfo, getWidth()/10, (getHeight()*3/4)+5);
        g.drawString("Movie: " + movieLabel, getWidth()/3, (getHeight()/4)+5);
        g.drawString("Date: " + date, getWidth()/3, (getHeight()*3/4)+5);
        g.drawString("Auditorium: " + audID, (getWidth()/3)*2, (getHeight()/4)+5);
        g.drawString("Seats: " + seats, (getWidth()/3)*2, (getHeight()*3/4)+5);
    }
}
