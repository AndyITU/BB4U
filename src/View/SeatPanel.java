package View;

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
    public Seat[][] seats;
    private int rowID;
    private int columnID;


    public SeatPanel(int row, int column) {
        super(new GridLayout(0,10));
        rowID = row;
        columnID = column;
        seats = new Seat[rowID][columnID];
        for ( int i = 0; i < rowID; i++)
            for ( int k = 0; k < columnID; k++){
            Seat s = new Seat();
                this.add(s);
                seats[i][k] = s;

            }
        setPreferredSize(new Dimension(1000,300));
        setVisible(true);
    }
    public SeatPanel getSeatPanel() {
        return this;
    }
    public void startBook() {
        for (int i = 0; i < rowID; i++) {
            for (int k = 0; k < columnID; k++) {
                seats[i][k].setBooked(!seats[i][k].getBooked());
            }
        }
    }

}
