package View;

import Model.Show;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
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
    private final JLabel movieHeader = new JLabel("Film:");
    private final JLabel auditoriumHeader = new JLabel("Sal nr:");
    private final JButton bookButton;
    private int availableSeats;
    private int totalSeats;
    private JLabel overallSeatInfo;
    private JFormattedTextField contactPhone;
    private JFormattedTextField contactName;
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd. MMMM - yyyy HH:mm", new Locale("da", "DK"));



    InformationPanel(Show show, int seatsReserved, int seatsTotal) {
        super(new GridLayout(2, 2));
        //setLayout(new GridLayout(2, 2));
        setPreferredSize(new Dimension(1000, 50));
        setVisible(true);
        bookButton = new JButton("Book");
        availableSeats = seatsTotal - seatsReserved;
        totalSeats = seatsTotal;
        JPanel contactBox = new JPanel();
        contactBox.setLayout(new GridLayout(2, 2));
        add(createLeftInformationBox(show.getMovie(), show.getAud_id()));
        add(bookButton);
        add(createTimeInformationBox(show.getDate(), show.getDuration()));
        add(contactBox);
        try {
            MaskFormatter phoneRule = new MaskFormatter("########");
            MaskFormatter nameRule = new MaskFormatter("***************************************************");
            nameRule.setValidCharacters("ABCDEFGHIJKLMNOPQRSTUVXYZWÆØÅabcdefghijklmnopqrstuvxyzwæøå ");
            contactPhone = new JFormattedTextField();
            contactName = new JFormattedTextField();
            nameRule.install(contactName);
            phoneRule.install(contactPhone);
            contactBox.add(new JLabel("Contact Number:"));
            contactBox.add(contactPhone);
            contactBox.add(new JLabel("Contact Name:"));
            contactBox.add(contactName);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public JButton getBookButton() {
        return bookButton;
    }
    public String getCustomerName() {
        return contactName.getText();
    }
    public String getCustomerPhone()    {
        return contactPhone.getText();
    }
    public void resetCustomerInfo() {
        contactName.getText();
        //contactPhone.getText().replaceAll();

    }
    private JPanel createLeftInformationBox(String movie, int auditorium_id) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3,2));
        JLabel movieName = new JLabel(movie);
        JLabel auditoriumName = new JLabel(Integer.toString(auditorium_id));
        overallSeatInfo = new JLabel(availableSeats + "/" + totalSeats);
        infoPanel.add(movieHeader);
        infoPanel.add(movieName);
        infoPanel.add(auditoriumHeader);
        infoPanel.add(auditoriumName);
        infoPanel.add(new JLabel("Ledige sæder:"));
        infoPanel.add(overallSeatInfo);
        return infoPanel;
    }

    public void updateSeatInfo(int seatsReserved, int seatsTotal) {
        availableSeats = seatsTotal - seatsReserved;
        totalSeats = seatsTotal;
        remove(overallSeatInfo);
        overallSeatInfo =  new JLabel(availableSeats + "/" + totalSeats);
        add(overallSeatInfo);
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