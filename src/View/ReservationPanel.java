package View;

import Controller.Booking;
import Model.Auditorium;
import Model.Reservation;
import Model.Show;
//import javafx.scene.control.ListCell;

import javax.swing.*;
import java.awt.*;
/**
 * ReservationPanel is a JPanel consisting of other JPanels. For the list of reservations it creates and adds a ScrollPane to itself
 * instead of adding the actual class.
 *
 * It uses a BoxLayout such that all the components are laid on top of each other.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class ReservationPanel extends JPanel {


    private SeatPanel sPanel;
    private CanvasPanel cPanel;
    private ReservationList rlPanel;
    private ReservationSearch rsPanel;
    public ReservationPanel(Reservation[] reservations, Show show, Auditorium auditorium) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1000, 200));
        cPanel = new CanvasPanel();
        rlPanel = new ReservationList(reservations);
        rlPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rsPanel = new ReservationSearch();
        sPanel = new SeatPanel(auditorium.getRows(), auditorium.getCols(), Booking.getReservedSeats(show.getId()),false);
        JScrollPane scrollPane = new JScrollPane(rlPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(rsPanel);
        add(scrollPane);
        setVisible(true);
        add(cPanel);
        add(sPanel);
    }

    /**
     * This method returns a reference to the ReservationSearch object in the class, whenever it is called.
     * @return rsPanel to the method caller.
     */
    public ReservationSearch getReservationSearch() {
        return rsPanel;
    }

    /**
     * This method returns a reference to the ReservationList object in the class, whenever it is called.
     * @return rlPanel to the method caller.
     */
    public ReservationList getReservationList() {
        return rlPanel;
    }
    /**
     * This method returns a reference to the SeatPanel object in the class, whenever it is called.
     * @return sPanel to the method caller.
     */
    public SeatPanel getReservationSeatPanel() {
        return sPanel;
    }

    /**
     * This method replaces all objects in ReservationPanel with the same type of objects but with new data.
     * @param reservations the list of reservations that should be added to the ReservationList.
     * @param show_id the list of reserved seats are gotten with the use of an integer.
     * @param auditorium the auditorium which SeatPanel needs to represent.
     */
    public void updatePanels(Reservation[] reservations, int show_id, Auditorium auditorium) {
        removeAll();
        sPanel = new SeatPanel(auditorium.getRows(), auditorium.getCols(), Booking.getReservedSeats(show_id),false);
        cPanel = new CanvasPanel();
        rlPanel = new ReservationList(reservations);
        rlPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(rlPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(rsPanel);
        add(scrollPane);
        add(cPanel);
        add(sPanel);
        repaint();
        revalidate();
    }

}
