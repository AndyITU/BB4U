package View;

import Model.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ReservationEntry extends JButton implements MouseListener {

    private Reservation customer;
    private boolean isHighlighted;

    public ReservationEntry() {
        setSize(new Dimension (1000, 100));
        setMaximumSize(getSize());
        setMinimumSize(getSize());
        addMouseListener(this);
        setContentAreaFilled(false);
        setVisible(true);
        //customer = r;
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
        g.drawString("WilliamWallace", getWidth()/10, getHeight()/4);
        g.drawString("81202938", getWidth()/10, getHeight()*3/4);
        g.drawString("X-Men", getWidth()/2, getHeight()/4);
        g.drawString("27. January", getWidth()/2, getHeight()*3/4);
    }


}
