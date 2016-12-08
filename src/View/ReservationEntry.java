package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ReservationEntry extends JButton implements MouseListener {

    private String contactName;
    private String contactNumber;


    public ReservationEntry() {
        setSize(new Dimension (1000, 100));
        setMaximumSize(getSize());
        setMinimumSize(getSize());
        addMouseListener(this);
        setContentAreaFilled(false);
        setVisible(true);
    }
    public void paint(Graphics g) {
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

    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void newEntry(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(2,2,getWidth()-4,getHeight()-4);
        g.setColor(Color.BLACK);
        g.drawRect(2,2,getWidth()-4,getHeight()-4);
    }


}
