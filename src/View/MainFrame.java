package View;

import Controller.Booking;
import Controller.ViewController;
import Model.Auditorium;
import Model.Show;

import javax.swing.*;
import java.awt.*;

/**
 * MainFrame is the main class of the GUI, and an extension of JFrame. It is holding a reference to all of the other panels. The MainFrame is able
 * of switching between the Panels by invocation of the {@link #changeToPanel(JPanel)}.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class MainFrame extends JFrame
{
    private final BookingViewPanel bvPanel;
    private final ReservationPanel rPanel;
    private final SearchViewPanel searchPanel;
    private final ButtonPanel bPanel;
    private final Container pane;
    private JPanel currentPanel;

    /**
     * The constructor of MainFrame sets up all panels as new objects, with given parameters.
     * @param show the show to be used by BookingViewPanel and ReservationPanel.
     * @param auditorium the auditorium to be used by BookingViewPanel and ReservationPanel.
     * @param reservedSeats the amount of reserved seats in the movie.
     */

    public MainFrame(Show show, Auditorium auditorium, int reservedSeats) {
        super();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        bvPanel = new BookingViewPanel(show, auditorium, reservedSeats);
        bPanel = new ButtonPanel();
        rPanel = new ReservationPanel(Booking.getReservations(), show.getId(), auditorium);
        searchPanel = new SearchViewPanel(ViewController.getMovieTitles());

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1050,1000));

        setVisible(true);
        pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        changeToPanel(bvPanel);
        pack();
    }

    /**
     * This method returns an object of the type BookingViewPanel whenever it is called.
     * @return bvPanel to the method caller.
     */
    public BookingViewPanel getBookingPanel() {
        return bvPanel;
    }
    /**
     * This method returns an object of the type SearchPanel whenever it is called.
     * @return searchPanel to the method caller.
     */
    public SearchViewPanel getSearchPanel() {
        return searchPanel;
    }
    /**
     * This method returns an object of the type ButtonPanel whenever it is called.
     * @return bPanel to the method caller.
     */
    public ButtonPanel getButtonPanel() {
        return bPanel;
    }
    /**
     * This method returns an object of the type ReservationPanel whenever it is called.
     * @return rPanel to the method caller.
     */
    public ReservationPanel getReservationPanel() {
        return rPanel;
    }
    /**
     * This method returns an object of the type JPanel whenever it is called.
     * @return currentPanel to the method caller.
     */
    public JPanel getCurrentPanel() {
        return currentPanel;
    }

    /**
     * This method updates whatever panel the user is currently looking at with a new one.
     * @param panel the JPanel to be switched to.
     */
    public void changeToPanel (JPanel panel) {
        currentPanel = panel;
        pane.removeAll();
        pane.add(bPanel);
        pane.add(panel);
        repaint();
        pack();
    }

}