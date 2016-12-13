package View;

import Model.SeatModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * SeatPanel is an extension of JPanel that consists of all the seats that are in a given auditorium, hence the rows and columns.
 * It needs an array of ReservedSeats such that the {@link #startBooking(SeatModel[])} can begin. It also stores all
 * of the pictures that the individual seat need to use to draw different colors.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class SeatPanel extends JPanel {
    private final Seat[][] viewSeats;
    private final int rowID;
    private final int columnID;
    Image NORMAL_IMAGE = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    Image BOOKED_IMAGE = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    Image SELECTED_IMAGE = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    Image HIGHLIGHT_IMAGE = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

    /**
     * The constructor of SeatPanel creates a list of Seats, and tries to simulate what the auditorium looks like.
     * @param rows the amount of rows in a given auditorium.
     * @param columns the amount of columns in a given auditorium.
     * @param reservedSeats the reserved seats in a given auditorium
     * @param b whether or not the SeatPanel can be interacted with.
     */
    public SeatPanel(int rows, int columns, SeatModel[] reservedSeats, boolean b) {
        super(new GridLayout(0, columns));
        setMinimumSize(new Dimension(1000, 500));
        rowID = rows;
        columnID = columns;
        viewSeats = new Seat[rowID][columnID];
        try {
            NORMAL_IMAGE = ImageIO.read(Seat.class.getResourceAsStream("images/Green Seat2.png"))
                    .getScaledInstance(1000/columns, 500/rows, Image.SCALE_SMOOTH);
            BOOKED_IMAGE = ImageIO.read(Seat.class.getResourceAsStream("images/booked_seat.png"))
                    .getScaledInstance(1000/columns, 500/rows, Image.SCALE_SMOOTH);
            SELECTED_IMAGE = ImageIO.read(Seat.class.getResourceAsStream("images/Orange Seat.png"))
                    .getScaledInstance(1000/columns, 500/rows, Image.SCALE_SMOOTH);
            HIGHLIGHT_IMAGE = ImageIO.read(Seat.class.getResourceAsStream("images/teal_seat.png"))
                    .getScaledInstance(1000/columns+8, 500/rows+8, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < rowID; i++)
            for (int k = 0; k < columnID; k++) {
                Seat s = new Seat(i, k, b, this);
                this.add(s);
                viewSeats[i][k] = s;

            }
        setPreferredSize(new Dimension(1000, 300));
        setVisible(true);
        startBooking(reservedSeats);
    }

    /**
     * This method is used for when making a new reservation. SeatPanel holds a reference to all the seats that it contains. The way the method works
     * is by running through all of them, and checking whether the command .setBooked evaluates to true. If it does, that must mean that the seats were selected by the user,
     * and were not already occupied. It then returns all the seats that have been successfully booked.
     *
     * @return SeatModel[] containing the seats that were succesfully booked.
     */
    public SeatModel[] newBooking() {
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

    /**
     * This method is run in the start of the program, and when doing any sort of updates to SeatPanel.
     * It takes an array of SeatModels and sets them to be booked in the interface.
     * @param seats the list of seats that are already booked.
     */

    public void startBooking(SeatModel[] seats) {
        for (SeatModel Seat : seats) {
            viewSeats[Seat.getRow() - 1][Seat.getCol() - 1].setModelBooked(Seat.getIsBooked());
        }
    }

    /**
     * This method runs through all of the seats and changes whether they are clickable/unclickable.
     * @param b boolean used to determine if the seat should be clickable/unclickable.
     */

    public void setClickable(Boolean b) {
        for (int i = 0; i < rowID; i++) {
            for (int k = 0; k < columnID; k++) {
                viewSeats[i][k].setClickable(b);
            }
        }
    }

    /**
     * This method runs through some of the seats, and changes them to be selected.
     * @param seats SeatModel array that is used to set a certain amount of seats to be selected.
     */

    public void setSelectedSeats(SeatModel[] seats) {
        for (SeatModel seat : seats) {
            viewSeats[seat.getRow()-1][seat.getCol()-1].setBooked(false);
            viewSeats[seat.getRow()-1][seat.getCol()-1].isSelected(true);
        }
    }
}