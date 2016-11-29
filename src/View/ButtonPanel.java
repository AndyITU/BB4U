package View;

import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class View.ButtonPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ButtonPanel extends JPanel
{
    JButton searchButton;
    public ButtonPanel() {
        super();
        this.setLayout(new GridLayout(0,3));
        this.setPreferredSize(new Dimension(1000,50));
        this.setVisible(true);
        
        JButton firstPage = new JButton("Not View");
        JButton primaryPage = new JButton("Booking View");
        searchButton = new JButton("Search View");
        this.add(firstPage);
        this.add(primaryPage);
        this.add(searchButton);
        
    }

    public JButton getSearchButton() {
        return searchButton;
    }
        
}
