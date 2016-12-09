package View;

import Model.SeatModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Write a description of class FramePanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SeatPanel extends JPanel implements SeatPanel_Interface
{
    private final Seat[][] viewSeats;
    private final int rowID;
    private final int columnID;


    public SeatPanel(int rows, int columns, SeatModel[] reservedSeats) {
        super(new GridLayout(0,columns));
        rowID = rows;
        columnID = columns;
        viewSeats = new Seat[rowID][columnID];
        for ( int i = 0; i < rowID; i++)
            for ( int k = 0; k < columnID; k++){
                Seat s = new Seat(i,k);
                this.add(s);
                viewSeats[i][k] = s;

            }
        setPreferredSize(new Dimension(1000,300));
        setVisible(true);
        newBook(reservedSeats);
    }

    public SeatModel[] startBook() {
        ArrayList<SeatModel> newReservation = new ArrayList<>();
        for (int i = 0; i < rowID; i++) {
            for (int k = 0; k < columnID; k++) {
                if( viewSeats[i][k].setBooked(true) ) {
                    newReservation.add(new SeatModel(k+1,i+1,true));
                }
            }
        }
        return newReservation.toArray(new SeatModel[0]);
    }

    public void newBook(SeatModel[] Seats) {
        for (SeatModel Seat: Seats) {
            viewSeats[Seat.getRow() - 1][Seat.getCol() - 1].setModelBooked(Seat.getIsBooked());
        }
    }
}
