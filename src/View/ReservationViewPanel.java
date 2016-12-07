package View;

import Controller.Booking;
import Model.Auditorium;
import Model.Show;

import javax.swing.*;

/**
 * Created by Mikkel on 07-12-2016.
 */
public class ReservationViewPanel extends JPanel {


    private final SeatPanel sPanel;
    private final InformationPanel iPanel;
    private final CanvasPanel cPanel;

    public ReservationViewPanel(Show show, Auditorium auditorium, int reservedSeats) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        sPanel = new SeatPanel(auditorium.getRows(), auditorium.getCols(), Booking.getReservedSeats(show.getId()));
        iPanel = new InformationPanel(show, reservedSeats, auditorium.getCols()*auditorium.getRows());
        cPanel = new CanvasPanel();
        add(cPanel);
        add(sPanel);
        add(iPanel);
        setVisible(true);


    }

}
