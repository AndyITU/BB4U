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
    private ReservationView rvPanel;
    private ReservationSearch rsPanel;
    public ReservationPanel(Reservation[] reservations, Show show, Auditorium auditorium) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1000, 200));
        cPanel = new CanvasPanel();
        rvPanel = new ReservationView(reservations);
        rvPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rsPanel = new ReservationSearch();
        sPanel = new SeatPanel(auditorium.getRows(), auditorium.getCols(), Booking.getReservedSeats(show.getId()));
        JScrollPane scrollPane = new JScrollPane(rvPanel);
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
    public void updatePanels(Reservation[] reservations, Show show, Auditorium auditorium) {
        removeAll();
        sPanel = new SeatPanel(auditorium.getRows(), auditorium.getCols(), Booking.getReservedSeats(show.getId()));
        cPanel = new CanvasPanel();
        rvPanel = new ReservationView(reservations);
        rvPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rsPanel = new ReservationSearch();
        JScrollPane scrollPane = new JScrollPane(rvPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(rsPanel);
        add(scrollPane);
        add(cPanel);
        add(sPanel);
        repaint();
        revalidate();
    }

}
