package View;

import Model.Auditorium;
import Model.SeatModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Write a description of class View.ButtonPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class InformationPanel extends JPanel
{
    private SeatModel seat;
    private int bookCount;
    private SeatPanel panel;
    private JLabel movieHeader;
    private JLabel movieName;
    private JLabel auditoriumHeader;
    private JLabel auditoriumName;
    private JButton bookButton;
    private JButton methodButton;



    public InformationPanel(SeatPanel panel) {
        super();
        this.panel = panel;
        setLayout(new GridLayout(2,2));
        setPreferredSize(new Dimension(1000,50));
        setVisible(true);
        methodButton = new JButton("Method Testing");
        bookButton = new JButton("BOOK SELECTED");
        add(setFirst());
        add(setFirst());
        add(bookButton);
        add(methodButton);

    }
    public void setShowInfo(String movieName, int audNum) {
        this.movieName.setText(movieName);
        this.auditoriumName.setText(audNum+"");
        repaint();
    }
    public JButton getBookButton() {
        return bookButton;
    }

    public JButton getMethodButton() {
        return methodButton;
    }

    public SeatPanel getPanel() {
        return panel;
    }
    
    private JPanel setFirst() {
        JPanel panelOne = new JPanel();
        panelOne.setLayout(new GridLayout(3,2));

        movieHeader = new JLabel("Film:");
        movieName = new JLabel("Ikke Valgt");
        auditoriumHeader = new JLabel("Sal nr:");
        auditoriumName = new JLabel("Ikke Valgt");
        
        panelOne.add(movieHeader);
        panelOne.add(movieName);
        panelOne.add(auditoriumHeader);
        panelOne.add(auditoriumName);
        panelOne.add(new JLabel("Ledige sæder:"));
        panelOne.add(new JLabel("Ikke Valgt"));
        return panelOne;
    }
}