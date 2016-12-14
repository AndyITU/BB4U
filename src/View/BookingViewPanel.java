package View;

import Controller.Booking;
import Model.Auditorium;
import Model.Show;
import javax.swing.*;
/**
 * ReservationPanel is a JPanel consisting of other JPanels. It holds all the panels necessary when making a new booking.
 *
 * It uses a BoxLayout such that all the components are laid on top of each other.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class BookingViewPanel extends JPanel {

    private SeatPanel sPanel;
    private InformationPanel iPanel;

    /**
     * The constructor of BookingViewPanel creates and adds a CanvasPanel, SeatPanel, and an InformationPanel.
     * @param show the show to be used by the SeatPanel and InformationPanel.
     * @param auditorium the auditorium to be used by SeatPanel and InformationPanel.
     * @param reservedSeats the amount of reserved seats, to be used by InformationPanel.
     */
    public BookingViewPanel(Show show, Auditorium auditorium, int reservedSeats) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        updatePanels(show,auditorium,reservedSeats);
    }
    /**
     * This method replaces all objects in ReservationPanel with the same type of objects but with new data.
     * @param show the show to be used by the SeatPanel and InformationPanel.
     * @param auditorium the auditorium to be used by SeatPanel and InformationPanel.
     * @param reservedSeats the amount of reserved seats, to be used by InformationPanel.
     */
    public void updatePanels(Show show, Auditorium auditorium, int reservedSeats) {
        removeAll();
        sPanel = new SeatPanel(auditorium.getRows(), auditorium.getCols(), Booking.getReservedSeats(show.getId()), true);
        iPanel = new InformationPanel(show, reservedSeats, auditorium.getCols() * auditorium.getRows());
        CanvasPanel cPanel = new CanvasPanel();
        add(cPanel);
        add(sPanel);
        add(iPanel);
        setVisible(true);
    }
    /**
     * This method returns an object of the type JPanel whenever it is called.
     * @return iPanel that is currently in the BookingViewPanel.
     */
    public InformationPanel getInfoPanel() {
        return iPanel;
    }
    /**
     * This method returns an object of the type JPanel whenever it is called.
     * @return sPanel that is currently in the BookingViewPanel.
     */
    public SeatPanel getSeatPanel() {
        return sPanel;
    }

}
