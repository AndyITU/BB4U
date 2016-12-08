package View;

import Controller.Booking;
import Controller.ViewController;
import Model.Auditorium;
import Model.Show;

import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class BookingFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainFrame extends JFrame
{
    private static BookingViewPanel bvPanel;
    private static ReservationPanel rPanel;
    private static SearchViewPanel searchPanel;
    private static ButtonPanel bPanel;
    private static Container pane;

    public MainFrame(Show show, Auditorium auditorium, int reservedSeats) {
        super();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        bvPanel = new BookingViewPanel(show, auditorium, reservedSeats);
        bPanel = new ButtonPanel();
        rPanel = new ReservationPanel(Booking.getReservations(), show, auditorium);
        searchPanel = new SearchViewPanel(ViewController.getMovieTitles());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1050,1000));
        setVisible(true);
        pane = getContentPane();

        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(bPanel);
        pane.add(bvPanel);

        this.pack();
    }
    public void updateMoviePanel(Show show, Auditorium auditorium, int reservedSeats) {
        bvPanel.updatePanels(show, auditorium, reservedSeats);
        repaint();
        pack();
    }
    public BookingViewPanel getBookingPanel() {
        return bvPanel;
    }
    public SearchViewPanel getSearchPanel() {
        return searchPanel;
    }
    public ButtonPanel getButtonPanel() {
        return bPanel;
    }
    public ReservationPanel getReservationPanel() {
        return rPanel;
    }
    public void changeToPanel (JComponent panel) {
        pane.removeAll();
        pane.add(bPanel);
        pane.add(panel);
        repaint();
        pack();
    }

}