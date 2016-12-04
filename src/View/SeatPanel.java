package View;

import Controller.Booking;
import Model.Reservation;
import Model.SeatModel;

import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class FramePanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SeatPanel extends JPanel
{
    public Seat[][] viewSeats;
    private SeatModel[] testingSeats = new SeatModel[3];
    private int rowID;
    private int columnID;


    public SeatPanel(int rows, int columns) {
        super(new GridLayout(0,10));
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
        testingSeats[0] = new SeatModel(0,0, true);
        testingSeats[1] = new SeatModel(0,1, true);
        testingSeats[2] = new SeatModel(2,3, true);


        newBook(testingSeats);
    }
    SeatPanel getSeatPanel() {
        return this;
    }

    public void startBook() {
        for (int i = 0; i < rowID; i++) {
            for (int k = 0; k < columnID; k++) {
                viewSeats[i][k].setBooked(true);
                if( viewSeats[i][k].setBooked(true) ) {

                }
            }
        }
    }

    public void newBook(SeatModel[] Seats) {
        for (int i = 0; i < Seats.length; i++) {
            viewSeats[Seats[i].getRow()][Seats[i].getCol()].setModelBooked(Seats[i].getIsBooked());
        }
    }
}
