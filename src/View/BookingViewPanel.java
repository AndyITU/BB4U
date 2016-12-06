package View;

import Controller.Booking;
import Model.Auditorium;
import Model.Show;

import javax.swing.*;
import java.time.LocalDateTime;

/**
 * Created by arha on 12/2/2016.
 */
public class BookingViewPanel extends JPanel {

    private SeatPanel sPanel;
    private InformationPanel iPanel;
    private CanvasPanel cPanel;

    public BookingViewPanel(Show show, Auditorium auditorium, int reservedSeats) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        sPanel = new SeatPanel(auditorium.getRows(), auditorium.getCols(), Booking.getReservedSeats(show.getId()));
        iPanel = new InformationPanel(sPanel.getSeatPanel(), show, reservedSeats, auditorium.getCols()*auditorium.getRows());
        cPanel = new CanvasPanel();
        add(cPanel); add(sPanel); add(iPanel);
        setVisible(true);
    }

    public InformationPanel getInfoPanel() {
        return iPanel;
    }

    public SeatPanel getSeatPanel() {
        return sPanel;
    }

}
