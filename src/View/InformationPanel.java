package View;

import Model.Show;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.IllegalFormatException;
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
    private JTextField contactPhone = new JTextField();
    private JTextField contactName = new JTextField();
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd. MMMM - yyyy HH:mm", new Locale("da", "DK"));



    InformationPanel(SeatPanel panel, Show show, int seatsReserved, int seatsTotal) {
        super();
        this.panel = panel;
        setLayout(new GridLayout(2, 2));
        setPreferredSize(new Dimension(1000, 50));
        setVisible(true);
        bookButton = new JButton("BOOK SELECTED");
        // The website http://www.javalobby.org/java/forums/t48584.html was used to understand this concept.
        JPanel contactBox = new JPanel();
        contactBox.setLayout(new GridLayout(2, 2));
        add(createLeftInformationBox(show.getMovie(), show.getAud_id(), seatsReserved, seatsTotal));
        add(bookButton);
        add(createTimeInformationBox(show.getDate(), show.getDuration()));
        add(contactBox);
        try {
            MaskFormatter phoneRule = new MaskFormatter("########");
            MaskFormatter nameRule = new MaskFormatter("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
            contactPhone = new JFormattedTextField(phoneRule);
            contactName = new JFormattedTextField(nameRule);
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
        contactName.setText("");
        contactPhone.setText("");
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