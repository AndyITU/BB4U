package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Write a description of class Square here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Seat extends JButton implements MouseListener
{
    private boolean isSelected;
    private boolean isHighlighted;
    private boolean isBooked;
    private int clickCount;
    private int booleanCount;
    /**
     * Constructor for objects of class Square
     */
    Seat() {
        setPreferredSize(new Dimension(10,10));
        this.setSize(new Dimension(getWidth()-getWidth()/5, getHeight()-getHeight()/5));
        this.addMouseListener(this);
        this.setContentAreaFilled(false);
    }
    
    public void paint (Graphics g) {
        makeSquare(g);
    }
    
    private void makeSquare(Graphics g) {
        if (isSelected) {
            g.setColor(Color.CYAN);
        }
            else if (isHighlighted) {
            g.setColor(Color.YELLOW);
        }
            else if (isBooked) {
            g.setColor(Color.RED);
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

    public void mouseClicked(MouseEvent e) {
        isSelected = !isSelected;
        repaint();
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
        if (!isSelected) {
            isSelected = !isSelected;
        }
        System.out.println("I got pressed");
    }
    public void mouseReleased(MouseEvent e) {
        // Maybe do something?
    }
    public boolean getBooked() {
        return isBooked;
    }
    public void setBooked(Boolean b) {
        if (isSelected) {
                isBooked = b;
                isSelected = false;
            repaint();
        }
    }

}
  
