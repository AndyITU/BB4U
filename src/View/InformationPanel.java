package View;

import Model.SeatModel;
import Model.Show;

import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class View.ButtonPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class InformationPanel extends JPanel
{
    private SeatModel seat;
    private SeatPanel panel;
    private JLabel movieHeader;
    private JLabel movieName;
    private JLabel auditoriumHeader;
    private JLabel auditoriumName;
    private JButton bookButton;
    private JButton methodButton;



    public InformationPanel(SeatPanel panel, Show show, int seatsReserved, int seatsTotal) {
        super();
        this.panel = panel;
        setLayout(new GridLayout(2,2));
        setPreferredSize(new Dimension(1000,50));
        setVisible(true);
        methodButton = new JButton("Method Testing");
        bookButton = new JButton("BOOK SELECTED");
        add(setFirst(show.getMovie(), show.getAud_id(), seatsReserved, seatsTotal));
        add(new JPanel());
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
    
    private JPanel setFirst(String movie, int auditorium_id, int seatsReserved, int seatsTotal) {
        JPanel panelOne = new JPanel();
        panelOne.setLayout(new GridLayout(3,2));

        movieHeader = new JLabel("Film:");
        movieName = new JLabel(movie);
        auditoriumHeader = new JLabel("Sal nr:");
        auditoriumName = new JLabel(Integer.toString(auditorium_id));
        
        panelOne.add(movieHeader);
        panelOne.add(movieName);
        panelOne.add(auditoriumHeader);
        panelOne.add(auditoriumName);
        panelOne.add(new JLabel("Ledige sæder:"));
        panelOne.add(new JLabel(seatsReserved+"/"+seatsTotal));
        return panelOne;
    }
}