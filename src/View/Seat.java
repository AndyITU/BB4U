package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Seat extends JButton implements MouseListener
{
    private boolean isSelected;
    private boolean isHighlighted;
    private boolean isBooked;
    private boolean isClickable;
    private final int rowID;
    private final int columnID;

    public Seat(int row, int column, boolean b) {
        setPreferredSize(new Dimension(10,10));
        setSize(new Dimension(getWidth()-getWidth()/5, getHeight()-getHeight()/5));
        addMouseListener(this);
        setContentAreaFilled(false);
        setFocusable(false);
        rowID = row+1;
        columnID = column+1;
        isClickable = b;
    }
    
    public void paint (Graphics g) {
        makeSquare(g);
        seatName(rowID,columnID, g);
    }
    
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

    public void seatName(int k, int s, Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Row:"+k, getWidth()/10,getHeight()-getHeight()/4);
        g.drawString("Seat:"+s, getWidth()/10, getHeight()-(getHeight()/4*3));
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
    public boolean setBooked(Boolean b) {
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
    public void setClickable(Boolean b) {
        isClickable = b;
    }
    public void setSelectedSomething(Boolean b) {
        isSelected = true;
        repaint();
    }
    public void setModelBooked(Boolean b) {
        isBooked = b;
        isSelected = false;
        repaint();
    }
}
  
