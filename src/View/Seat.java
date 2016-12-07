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
    private final int rowID;
    private final int columnID;

    public Seat(int row, int column) {
        setPreferredSize(new Dimension(10,10));
        setSize(new Dimension(getWidth()-getWidth()/5, getHeight()-getHeight()/5));
        addMouseListener(this);
        setContentAreaFilled(false);
        rowID = row+1;
        columnID = column+1;

    }
    
    public void paint (Graphics g) {
        makeSquare(g);
        seatName(rowID,columnID, g);
    }
    
    private void makeSquare(Graphics g) {
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

    private void seatName(int k, int s, Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Row:"+k, getWidth()/10,getHeight()-getHeight()/4);
        g.drawString("Seat:"+s, getWidth()/10, getHeight()-(getHeight()/4*3));
    }

    public void mouseClicked(MouseEvent e) {
        if (!isBooked) {
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
        isHighlighted = true;
        repaint();
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public boolean setBooked(Boolean b) {
        if (isSelected) {
            isBooked = b;
            isSelected = false;
            repaint();
            return true;
        }
        return false;
    }
    public void setModelBooked(Boolean b) {
        isBooked = b;
        isSelected = false;
        repaint();
    }
}
  
