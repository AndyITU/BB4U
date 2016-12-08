package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ReservationEntry extends JButton implements MouseListener {

    public ReservationEntry() {
        setSize(new Dimension (1000, 40));
        setMaximumSize(getSize());
        addMouseListener(this);
        setVisible(true);
    }
    public void paint(Graphics g) {
    }

    public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(null,"Would you like to edit or remove this reservation?");
    }
    public void mouseExited(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }


}
