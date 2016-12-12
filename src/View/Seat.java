package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Write a description of class BookingFrame here.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class Seat extends JButton implements MouseListener
{
    private boolean isSelected;
    private boolean isHighlighted;
    private boolean isBooked;
    private boolean isClickable;
    private final int rowID;
    private final int columnID;
    private final SeatPanel seatPanel;


    Seat(int row, int column, boolean b, SeatPanel seatPanel) {
        setPreferredSize(new Dimension(10,10));
        setSize(new Dimension(getWidth()-getWidth()/5, getHeight()-getHeight()/5));
        addMouseListener(this);
        setContentAreaFilled(false);
        setFocusable(false);
        rowID = row+1;
        columnID = column+1;
        isClickable = b;
        this.seatPanel = seatPanel;
    }

    public void paint (Graphics g) {
        if (isBooked) {
            g.drawImage(seatPanel.BOOKED_IMAGE, 0,0, null );
        }
        else if (isSelected) {
            g.drawImage(seatPanel.SELECTED_IMAGE, 0,0, null );
        }
        else if (isHighlighted) {
            g.drawImage(seatPanel.HIGHLIGHT_IMAGE, -4,-4, null );
        }
        else {
            g.drawImage(seatPanel.NORMAL_IMAGE, 0,0, null );
        }
        seatName(rowID,columnID, g);
    }
// The old method of making squares - Outdated -
    /**
    public void makeSquare(Graphics g) {
        if (isBooked) {
            g.setColor(Color.red);
        }
            else if (isSelected) {
            g.setColor(Color.pink);
        }
            else if (isHighlighted) {
            g.setColor(Color.yellow);
        }
            else {
                g.setColor(Color.GREEN);
            }

        g.fillRect(
        getWidth()/10,
        getHeight()/10,
        getWidth()-getWidth()/5,
        getHeight()-getHeight()/5);
        
        g.setColor(Color.BLACK);
        g.drawRect(
        getWidth()/10,
        getHeight()/10,
        getWidth()-getWidth()/5,
        getHeight()-getHeight()/5);
    }
     */

    void seatName(int k, int s, Graphics g) {
        g.setFont(new Font("Haettenschweiler", Font.PLAIN, 12));
        g.drawString("R: "+k, getWidth()/10,(getHeight()));
        g.drawString("S: "+s, (getWidth()/2), (getHeight()));
    }

    public void mouseClicked(MouseEvent e) {
        if (!isBooked && isClickable) {
            isSelected = !isSelected;
            repaint();
        }
        /* Idea for selecting multiple seats: Holding shift down will select everything
         if (e.isShiftDown())  {
            e.getLocation();
            repaint();
        }
         */
    }
    public void mouseExited(MouseEvent e) {
        isHighlighted = false;
        repaint();
    }
    public void mouseEntered(MouseEvent e) {
        if (isClickable)
        isHighlighted = true;
        repaint();
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    boolean setBooked(Boolean b) {
        if (isSelected && isClickable && b) {
            isBooked = b;
            isSelected = false;
            repaint();
            return true;
        } else if(isClickable && !b) {
            isBooked = b;
            isSelected = false;
            repaint();
            return true;
        }
        return false;
    }
    void setClickable(Boolean b) {
        isClickable = b;
    }
    void isSelected(Boolean b) {
        isSelected = true;
        repaint();
    }
    void setModelBooked(Boolean b) {
        isBooked = b;
        isSelected = false;
        repaint();
    }
}
  
