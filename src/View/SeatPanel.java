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
public class SeatPanel extends JPanel
{
    public Seat[][] viewSeats;
    private int rowID;
    private int columnID;


    public SeatPanel(int rows, int columns, SeatModel[] reservatedSeats) {
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
        newBook(reservatedSeats);
    }
    SeatPanel getSeatPanel() {
        return this;
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
        System.out.print(newReservation.size());
        return newReservation.toArray(new SeatModel[0]);
    }

    public void newBook(SeatModel[] Seats) {
        for (int i = 0; i < Seats.length; i++) {
            viewSeats[Seats[i].getRow()-1][Seats[i].getCol()-1].setModelBooked(Seats[i].getIsBooked());
        }
    }
}
