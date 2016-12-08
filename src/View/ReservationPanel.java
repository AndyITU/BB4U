package View;

import Controller.Booking;
import Model.Auditorium;
import Model.Show;
//import javafx.scene.control.ListCell;

import javax.swing.*;
import java.awt.*;

public class ReservationPanel extends JPanel {


    private SeatPanel sPanel;
    private CanvasPanel cPanel;
    private ReservationView rvPanel;
    private ReservationSearch rsPanel;
    public ReservationPanel(Show show, Auditorium auditorium) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1000, 200));
        sPanel = new SeatPanel(auditorium.getRows(), auditorium.getCols(), Booking.getReservedSeats(show.getId()));
        cPanel = new CanvasPanel();
        rvPanel = new ReservationView();
        rvPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rsPanel = new ReservationSearch();
        JScrollPane scrollPane = new JScrollPane(rvPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(rsPanel);
        add(scrollPane);
        add(cPanel);
        add(sPanel);
        setVisible(true);
    }

}
