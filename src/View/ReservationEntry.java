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

public class ReservationEntry extends JButton implements MouseListener {

    private Reservation customer;
    private boolean isHighlighted;
    private int seats;
    private int audID;
    private String date;
    private String movie;
    private String contactInfo;
    private String contactName;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd. MMMM - yyyy HH:mm", new Locale("da", "DK"));

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
        movie = s.getMovie();
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
        // Inspired by http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
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
        g.drawString("Movie: " + movie, getWidth()/3, (getHeight()/4)+5);
        g.drawString("Date: " + date, getWidth()/3, (getHeight()*3/4)+5);
        g.drawString("Auditorium: " + audID, (getWidth()/3)*2, (getHeight()/4)+5);
        g.drawString("Seats: " + seats, (getWidth()/3)*2, (getHeight()*3/4)+5);
    }


}
