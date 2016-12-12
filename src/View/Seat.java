package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The "Seat" component works in extension of a JButton.
 * It is supposed to represent a graphical expression of a Seat, and will assist in
 * creating new reservations for the user. Mainly, it uses states to determine what
 * sort of image it should draw, which it does by the aid of a MouseListener.
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



    /**
     * This method draws the graphical display of the Seat. Depending on what state the individual seat is in,
     * the method will draw a corresponding image.
     */
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
        g.setFont(new Font("Haettenschweiler", Font.PLAIN, 12));
        g.drawString("R: "+rowID, getWidth()/10,(getHeight()));
        g.drawString("S: "+columnID, (getWidth()/2), (getHeight()));
    }
    //The old method of making squares - Outdated -
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
    /**
     * This method initiates the first step of a seat being booked. Once it has been selected, it will be available for booking,
     * but can still be deselected should the user change his or hers mind.
     * This method makes use of the MouseListener implementation.
     */
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

    /**
     * When hovering over the Seat component with the mouse, Seat will change state, and redraw itself in a new color.
     * This method makes use of the MouseListener implementation.
     */
    public void mouseExited(MouseEvent e) {
        isHighlighted = false;
        repaint();
    }
    /**
     * After hovering inside the Seat component with the mouse, hovering out will change state of Seat, and it will redraw itself in a new color.
     * This method makes use of the MouseListener implementation.
     */
    public void mouseEntered(MouseEvent e) {
        if (isClickable)
        isHighlighted = true;
        repaint();
    }

    /**
     * Unused method
     */
    public void mousePressed(MouseEvent e) {
    }
    /**
     * Unused method
     */
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * This method changes the availability of Seat. If it is booked, it looses most functionality.
     * @param b , used for selecting whether Seat should be booked, or unbooked.
     * @return true or false whether the method was a success or not.
     */
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

    /**
     * This method is used to determine if the Seat is able of changing states.
     * This is important in ReservationList when editing reservations.
     * See {@link ReservationList}
     *
     * @param b boolean for setting clickable/unclickable.
     */
    void setClickable(Boolean b) {
        isClickable = b;
    }

    /**
     * This method is used to change the isSelected state of Seat.
     * @param b boolean for setting selected/unselected.
     */
    void isSelected(Boolean b) {
        isSelected = b;
        repaint();
    }

    /**
     * This method is used to change the isBooked state of Seat. It is very similiar to {@link #setBooked}
     * but it is necessary for drawing the already reserved Seats, that was created before the program was launched.
     * @param b boolean for setting booked/unbooked.
     */
    void setModelBooked(Boolean b) {
        isBooked = b;
        isSelected = false;
        repaint();
    }
}
  
