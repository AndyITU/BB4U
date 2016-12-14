package Model;
/**
 * Write a description of class BookingFrame here.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class SeatModel {
    private final int col, row;

    public SeatModel(int col, int row) {
        this.col = col;
        this.row = row;
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