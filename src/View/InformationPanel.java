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
 * InformationPanel is an extension of JPanel. It is used for keeping tabs of all the necessary information about a show. (Date, duration, auditorium, etc.)
 *
 * It holds the reference to the bookButton that is used for booking seats, and two JFormattedTextFields, which are used for typing in user contact info.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
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

    /**
     * The constructor of InformationPanel takes information about what the current show is, and then displays it for the user to review.
     * @param show the show that is currently displayed in the BookingViewPanel.
     * @param seatsReserved the amount of seats that are reserved in the current show.
     * @param seatsTotal the amount of total seats that are available in the current show.
     */
    InformationPanel(Show show, int seatsReserved, int seatsTotal) {
        super(new GridLayout(2, 2));
        setPreferredSize(new Dimension(1000, 50));
        setVisible(true);
        bookButton = new JButton("Book");
        availableSeats = seatsTotal - seatsReserved;
        totalSeats = seatsTotal;
        JPanel contactBox = new JPanel();
        contactBox.setLayout(new GridLayout(2, 2));
        add(createMovieInformationBox(show.getMovie(), show.getAud_id()));
        add(bookButton);
        add(createTimeInformationBox(show.getDate(), show.getDuration()));
        add(contactBox);
        try {
            MaskFormatter phoneRule = new MaskFormatter("########");
            contactPhone = new JFormattedTextField();
            contactName = new JFormattedTextField();
            phoneRule.install(contactPhone);
            contactBox.add(new JLabel("Contact Number:"));
            contactBox.add(contactPhone);
            contactBox.add(new JLabel("Contact Name:"));
            contactBox.add(contactName);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method returns an object of the type JButton whenever it is called.
     * @return bookButton that is currently in the SearchPanel
     */
    public JButton getBookButton() {
        return bookButton;
    }
    /**
     * This method returns the currently typed text in a JFormattedTextField whenever it is called.
     * @return text currently written in contactName.
     */
    public String getCustomerName() {
        return contactName.getText();
    }
    /**
     * This method returns the currently typed text in a JFormattedTextField whenever it is called.
     * @return text currently written in contactPhone.
     */
    public String getCustomerPhone()    {
        return contactPhone.getText();
    }
    /**
     * This method invokes a method on both JFormattedTextFields in InformationPanel whenever it is called, which resets them to an empty string.
     */
    public void resetCustomerInfo() {
        contactPhone.setText("");
        contactName.setText("");
    }

    /**
     * This method returns an object of the type JPanel whenever it is called. It writes information about
     * @param movie
     * @param auditorium_id
     * @return
     */
    private JPanel createMovieInformationBox(String movie, int auditorium_id) {
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