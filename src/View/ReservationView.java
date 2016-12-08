package View;

import Model.Reservation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mikkel on 07-12-2016.
 */
public class ReservationView extends JPanel{

    public ReservationView(Reservation[] reservations) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1000, 500));
        for (int i = 0; i < reservations.length; i++) {
            add(Box.createRigidArea(new Dimension(0,5)));
            add(new ReservationEntry(reservations[i]));
        }
    }
}
