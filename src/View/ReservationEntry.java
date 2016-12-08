package View;

import Model.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ReservationEntry extends JButton implements MouseListener {

    private Reservation customer;
    private boolean isHighlighted;

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
        g.fillRect(2,2,getWidth()-4,getHeight()-4);
        g.setColor(Color.BLACK);
        g.drawRect(2,2,getWidth()-4,getHeight()-4);
        g.setColor(Color.WHITE);
        g.drawString(customer.getName(), getWidth()/10, (getHeight()/4)+5);
        g.drawString(customer.getContact_info(), getWidth()/10, (getHeight()*3/4)+5);
        g.drawString(customer.getAud_id()+"", getWidth()/2, (getHeight()/4)+5);
        g.drawString(customer.getShow_id()+"", getWidth()/2, (getHeight()*3/4)+5);
    }


}
