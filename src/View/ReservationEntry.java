package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ReservationEntry extends JButton implements MouseListener {

    JOptionPane selectOptions = new JOptionPane();

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
        // JOptionPane.showOptionDialog()Dialog(null,"Would you like to edit or remove this reservation?","Reservation edit",JOptionPane.YES_NO_CANCEL_OPTION);
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
