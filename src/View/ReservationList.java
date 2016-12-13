package View;

import Model.Reservation;

import javax.swing.*;
import java.awt.*;

/**
 * ReservationList is an extension of JPanel.
 *
 * It creates a list of reservation entries for the user to interact with.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */

public class ReservationList extends JPanel{

    ReservationList(Reservation[] reservations) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1000, 70*reservations.length));
        setMaximumSize(new Dimension(1000, 2000));
        updateEntries(reservations);
    }

    /**
     * This method replaces all the old reservations with a list of new reservations.
     * @param reservations the new reservations to be inserted into the list.
     */
    public void updateEntries(Reservation[] reservations) {
        removeAll();
        setPreferredSize(new Dimension(1000, 70*reservations.length));
        for (Reservation r : reservations) {
            add(Box.createRigidArea(new Dimension(0, 5)));
            add(new ReservationEntry(r));
        }
    }
}
