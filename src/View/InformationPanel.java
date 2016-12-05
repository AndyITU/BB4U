package View;

import Model.SeatModel;
import Model.Show;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private JLabel movieHeader = new JLabel("Film:");
    private JLabel auditoriumHeader = new JLabel("Sal nr:");

    private JLabel movieName;
    private JLabel auditoriumName;
    private JLabel dateName;
    private JLabel durationName;
    private JButton bookButton;
    private JButton methodButton;
    private JTextField contactInfo;



    public InformationPanel(SeatPanel panel, Show show, int seatsReserved, int seatsTotal) {
        super();
        this.panel = panel;
        setLayout(new GridLayout(2,2));
        setPreferredSize(new Dimension(1000,50));
        setVisible(true);
        methodButton = new JButton("Method Testing");
        bookButton = new JButton("BOOK SELECTED");
        contactInfo = new JFormattedTextField();
        contactInfo.setMaximumSize(new Dimension(getWidth(), getHeight()/2));
        add(createLeftInformationBox(show.getMovie(), show.getAud_id(), seatsReserved, seatsTotal));
        add(bookButton);
        add(createTimeInformationBox(show.getDate(), show.getDuration()));
        JPanel asd = new JPanel();
        asd.setLayout(new GridLayout(2,2));
        add(asd);
        asd.add(new JLabel("Contact Number:"));
        asd.add(contactInfo);
        asd.add(new JLabel("Contact Name:"));
        asd.add(new JFormattedTextField());
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
    
    private JPanel createLeftInformationBox(String movie, int auditorium_id, int seatsReserved, int seatsTotal) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3,2));
        movieName = new JLabel(movie);
        auditoriumName = new JLabel(Integer.toString(auditorium_id));
        infoPanel.add(movieHeader);
        infoPanel.add(movieName);
        infoPanel.add(auditoriumHeader);
        infoPanel.add(auditoriumName);
        infoPanel.add(new JLabel("Ledige sæder:"));
        infoPanel.add(new JLabel(seatsTotal - seatsReserved+"/"+seatsTotal));
        return infoPanel;
    }
    private JPanel createTimeInformationBox(LocalDateTime time, LocalTime duration) {
        JLabel dateHeader = new JLabel("Tidspunkt:");
        JLabel durationHeader = new JLabel("Afspilningstid:");
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new GridLayout(2, 2));
        dateName = new JLabel(time.getDayOfMonth()+". " + time.getMonth().toString() +" - "+ time.getYear() + " " +  time.getHour() + ":" + time.getMinute());
        durationName = new JLabel(duration.toString());
        timePanel.add(dateHeader);
        timePanel.add(dateName);
        timePanel.add(durationHeader);
        timePanel.add(durationName);
        return timePanel;
    }
}