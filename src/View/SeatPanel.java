package View;

import Model.SeatModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Write a description of class FramePanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SeatPanel extends JPanel {
    private final Seat[][] viewSeats;
    private final int rowID;
    private final int columnID;
    static Image NORMAL_IMAGE = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    static Image BOOKED_IMAGE = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    static Image SELECTED_IMAGE = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    static Image HIGHLIGHT_IMAGE = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);


    public SeatPanel(int rows, int columns, SeatModel[] reservedSeats, boolean b) {
        super(new GridLayout(0, columns));
        rowID = rows;
        columnID = columns;
        viewSeats = new Seat[rowID][columnID];
        try {
            NORMAL_IMAGE = ImageIO.read(Seat.class.getResourceAsStream("images/normal_seat.png"))
                    .getScaledInstance(1000/columns, 500/rows, Image.SCALE_SMOOTH);
            BOOKED_IMAGE = ImageIO.read(Seat.class.getResourceAsStream("images/booked_seat.png"))
                    .getScaledInstance(1000/columns, 500/rows, Image.SCALE_SMOOTH);
            SELECTED_IMAGE = ImageIO.read(Seat.class.getResourceAsStream("images/selected_seat.png"))
                    .getScaledInstance(1000/columns, 500/rows, Image.SCALE_SMOOTH);
            HIGHLIGHT_IMAGE = ImageIO.read(Seat.class.getResourceAsStream("images/highlight_seat.png"))
                    .getScaledInstance(1000/columns, 500/rows, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < rowID; i++)
            for (int k = 0; k < columnID; k++) {
                Seat s = new Seat(i, k, b);
                this.add(s);
                viewSeats[i][k] = s;

            }
        setPreferredSize(new Dimension(1000, 300));
        setVisible(true);
        newBook(reservedSeats);
    }

    public SeatModel[] startBook() {
        ArrayList<SeatModel> newReservation = new ArrayList<>();
        for (int i = 0; i < rowID; i++) {
            for (int k = 0; k < columnID; k++) {
                if (viewSeats[i][k].setBooked(true)) {
                    newReservation.add(new SeatModel(k + 1, i + 1, true));
                }
            }
        }
        return newReservation.toArray(new SeatModel[0]);
    }

    public void newBook(SeatModel[] seats) {
        for (SeatModel Seat : seats) {
            viewSeats[Seat.getRow() - 1][Seat.getCol() - 1].setModelBooked(Seat.getIsBooked());
        }
    }

    public void setClickable(Boolean b) {
        for (int i = 0; i < rowID; i++) {
            for (int k = 0; k < columnID; k++) {
                viewSeats[i][k].setClickable(b);
            }
        }
    }
    public void setClickable(SeatModel[] seats) {
        for (SeatModel s: seats) {
            viewSeats[s.getRow()-1][s.getCol()-1].setClickable(true);
        }
    }
    public void setSelectedSeats(SeatModel[] seats) {
        for (SeatModel seat : seats) {
            viewSeats[seat.getRow()-1][seat.getCol()-1].setBooked(false);
            viewSeats[seat.getRow()-1][seat.getCol()-1].setSelectedSomething(true);
        }
    }
}