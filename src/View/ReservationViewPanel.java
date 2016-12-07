package View;

import Controller.Booking;
import Model.Auditorium;
import Model.Show;

import javax.swing.*;

/**
 * Created by Mikkel on 07-12-2016.
 */
public class ReservationViewPanel extends JPanel {


    private SeatPanel sPanel;
    private CanvasPanel cPanel;
    private JFormattedTextField searchReservations;

    public ReservationViewPanel(Show show, Auditorium auditorium) {
        //searchReservations = new JFormattedTextField();
        //searchReservations.setColumns(10);
        //add(searchReservations);
        for (int i = 0; i < 10; i++) {
            ReservationEntry entry = new ReservationEntry();
            add(entry);
        }
        sPanel = new SeatPanel(auditorium.getRows(), auditorium.getCols(), Booking.getReservedSeats(show.getId()));
        cPanel = new CanvasPanel();
        add(cPanel);
        add(sPanel);
        setVisible(true);
    }

}
