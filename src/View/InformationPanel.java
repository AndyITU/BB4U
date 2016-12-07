package View;

import Model.Show;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Write a description of class View.ButtonPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class InformationPanel extends JPanel
{
    private final SeatPanel panel;
    private final JLabel movieHeader = new JLabel("Film:");
    private final JLabel auditoriumHeader = new JLabel("Sal nr:");
    private final JButton bookButton;
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd. MMMM - yyyy HH:mm", new Locale("da", "DK"));



    InformationPanel(SeatPanel panel, Show show, int seatsReserved, int seatsTotal) {
        super();
        this.panel = panel;
        setLayout(new GridLayout(2,2));
        setPreferredSize(new Dimension(1000,50));
        setVisible(true);
        bookButton = new JButton("BOOK SELECTED");
        JTextField contactInfo = new JFormattedTextField();
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
    /*public void setShowInfo(String movieName, int audNum) {
        this.movieName.setText(movieName);
        this.auditoriumName.setText(audNum+"");
        repaint();
    }*/
    public JButton getBookButton() {
        return bookButton;
    }

    public SeatPanel getPanel() {
        return panel;
    }
    
    private JPanel createLeftInformationBox(String movie, int auditorium_id, int seatsReserved, int seatsTotal) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3,2));
        JLabel movieName = new JLabel(movie);
        JLabel auditoriumName = new JLabel(Integer.toString(auditorium_id));
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
        JLabel dateName = new JLabel(time.format(format));
        JLabel durationName = new JLabel(duration.toString());
        timePanel.add(dateHeader);
        timePanel.add(dateName);
        timePanel.add(durationHeader);
        timePanel.add(durationName);
        return timePanel;
    }
}