package View;

import Model.Reservation;

import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class BookingFrame here.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */

public class ReservationList extends JPanel{

    ReservationList(Reservation[] reservations) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1000, 70*reservations.length));
        setMaximumSize(new Dimension(1000, 2000));
        for (Reservation r : reservations) {
            add(Box.createRigidArea(new Dimension(0, 5)));
            add(new ReservationEntry(r));
        }
    }

    public void updateEntries(Reservation[] reservations) {
        removeAll();
        setPreferredSize(new Dimension(1000, 70*reservations.length));
        for (Reservation r : reservations) {
            add(Box.createRigidArea(new Dimension(0, 5)));
            add(new ReservationEntry(r));
        }
    }
}
