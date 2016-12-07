package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Mikkel on 07-12-2016.
 */
public class ReservationEntry extends JButton implements MouseListener {

    public ReservationEntry() {
        setPreferredSize(new Dimension(1000, 250));
        addMouseListener(this);
        setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {

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
