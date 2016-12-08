package View;

import Controller.Booking;
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
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd. MMMM - yyyy HH:mm", new Locale("da", "DK"));

    public ReservationEntry(Reservation res) {
        setSize(new Dimension (1000, 120));
        setMaximumSize(getSize());
        setMinimumSize(new Dimension(1000, 100));
        addMouseListener(this);
        setContentAreaFilled(false);
        setVisible(true);
        customer = res;
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
        JOptionPane.showOptionDialog(null, "Would you like to edit, or remove?", "Reservation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionList, optionList[2]);

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
        Show s = Booking.getShow(customer.getShow_id());
        g.fillRect(2,2,getWidth()-4,getHeight()-4);
        g.setColor(Color.BLACK);
        g.drawRect(2,2,getWidth()-4,getHeight()-4);
        g.setColor(Color.WHITE);
        g.drawString("Name: " + customer.getName(), getWidth()/10, (getHeight()/4)+5);
        g.drawString("Contact info: " + customer.getContact_info(), getWidth()/10, (getHeight()*3/4)+5);
        g.drawString("Movie: " + s.getMovie(), getWidth()/2, (getHeight()/4)+5);
        g.drawString("Date: " + s.getDate().format(format), getWidth()/2, (getHeight()*3/4)+5);
        g.drawString("Auditorium: " + customer.getAud_id(), getWidth()/10*9, (getHeight()/4)+5);
        g.drawString("Seats: " + customer.getSeats().length, getWidth()/10*9, (getHeight()*3/4)+5);
    }


}
