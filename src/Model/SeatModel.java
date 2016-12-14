package Model;
/**
 * Write a description of class BookingFrame here.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class SeatModel {
    private final int col, row;
    private final boolean isBooked;

    public SeatModel(int col, int row, boolean isBooked) {
        this.col = col;
        this.row = row;
        this.isBooked = isBooked;
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
    /**
     * This method returns an object of the type boolean whenever it is called.
     * @return isBooked that is currently in SeatModel.
     */
    public boolean getIsBooked() {
        return isBooked;
    }

    /*public void setBooked(boolean booked) {
        if(isBooked != booked)
            isBooked = booked;
    }*/
}