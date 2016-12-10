package View;

import Controller.Booking;
import Model.Auditorium;
import Model.Reservation;
import Model.Show;
//import javafx.scene.control.ListCell;

import javax.swing.*;
import java.awt.*;

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
    public ReservationSearch getReservationSearch() {
        return rsPanel;
    }
    public ReservationList getReservationList() {
        return rlPanel;
    }
    public SeatPanel getReservationSeatPanel() {
        return sPanel;
    }

    public void updatePanels(Reservation[] reservations, int show_id, Auditorium auditorium) {
        removeAll();
        sPanel = new SeatPanel(auditorium.getRows(), auditorium.getCols(), Booking.getReservedSeats(show_id),false);
        cPanel = new CanvasPanel();
        rlPanel = new ReservationList(reservations);
        rlPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rsPanel = new ReservationSearch();
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
