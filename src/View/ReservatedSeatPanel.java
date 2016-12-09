package View;

import Model.SeatModel;

import javax.swing.*;

/**
 * Created by Mikkel on 09-12-2016.
 */
public class ReservatedSeatPanel extends JPanel implements SeatPanel_Interface {
    private SeatPanel sPanel;

    public ReservatedSeatPanel(SeatPanel s) {
        sPanel = s;
    }

    public void newBook(SeatModel[] seats) {
        sPanel.newBook(seats);
    }

    public SeatModel[] startBook() {
        return sPanel.startBook();
    }
}
