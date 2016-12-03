package View;

import Controller.Booking;
import Model.Auditorium;
import Model.Show;

import javax.swing.*;

/**
 * Created by arha on 12/2/2016.
 */
public class BookingViewPanel extends JPanel {

    private SeatPanel sPanel;
    private InformationPanel iPanel;
    private CanvasPanel cPanel;

    public BookingViewPanel(Show show) {
        Auditorium auditorium = Booking.getAuditorium(show.getAud_id());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        sPanel = new SeatPanel(auditorium.getRows(), auditorium.getCols());
        iPanel = new InformationPanel(sPanel.getSeatPanel(), show, Booking.getReservedSeats(show.getId()).length, auditorium.getCols()*auditorium.getRows());
        cPanel = new CanvasPanel();
        add(cPanel); add(sPanel); add(iPanel);
        setVisible(true);
    }

    public InformationPanel getInfoPanel() {
        return iPanel;
    }
}
