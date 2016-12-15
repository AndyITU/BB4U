package Model;
/**
 * This is the SeatModel class. It includes all necessary information about a seat for the "Arrival of Bookings" software.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class SeatModel {
    private int col, row;

    /**
     * The constructor gives show all parameters as private values. It checks these values for valid input.
     * @param col what column the seat is located in.
     * @param row what row the seat is located in.
     */

    public SeatModel(int col, int row) {
        if (col > 0) this.col = col;
        else throw new IllegalArgumentException();
        if (row > 0) this.row = row;
        else throw new IllegalArgumentException();
    }
    /**
     * This method returns an object of the type integer whenever it is called.
     * @return col that is currently in SeatModel.
     */
    public int getCol() {
        return col;
    }
    /**
     * This method returns an object of the type integer whenever it is called.
     * @return isBooked that is currently in SeatModel.
     */
    public int getRow() {
        return row;
    }

}