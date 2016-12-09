package View;

import Controller.Booking;
import Model.Auditorium;
import Model.Show;
import javax.swing.*;

public class BookingViewPanel extends JPanel {

    private SeatPanel sPanel;
    private InformationPanel iPanel;
    private CanvasPanel cPanel;

    public BookingViewPanel(Show show, Auditorium auditorium, int reservedSeats) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        sPanel = new SeatPanel(auditorium.getRows(), auditorium.getCols(), Booking.getReservedSeats(show.getId()),true);
        iPanel = new InformationPanel(show, reservedSeats, auditorium.getCols()*auditorium.getRows());
        cPanel = new CanvasPanel();
        add(cPanel);
        add(sPanel);
        add(iPanel);
        setVisible(true);
    }

    public void updatePanels(Show show, Auditorium auditorium, int reservedSeats) {
        removeAll();
        sPanel = new SeatPanel(auditorium.getRows(), auditorium.getCols(), Booking.getReservedSeats(show.getId()), true);
        iPanel = new InformationPanel(show, reservedSeats, auditorium.getCols() * auditorium.getRows());
        cPanel = new CanvasPanel();
        add(cPanel);
        add(sPanel);
        add(iPanel);
        setVisible(true);
    }

    public InformationPanel getInfoPanel() {
        return iPanel;
    }

    public SeatPanel getSeatPanel() {
        return sPanel;
    }

}
