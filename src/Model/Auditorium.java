package Model;
/**
 * Write a description of class BookingFrame here.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class Auditorium {
    private final int id, rows, cols;

    public Auditorium(int id, int rows, int cols) {
        // Do something here that initializes all the seatModels using Database.getSeats(aud_id)
        this.id = id;
        this.rows = rows;
        this.cols = cols;
    }
    /**
     * This method returns an object of the type integer whenever it is called.
     * @return id of the auditorium.
     */
    public int getId() {
        return id;
    }

    /**
     * This method returns an object of the type integer whenever it is called.
     * @return how many rows that are in the auditorium
     */
    public int getRows() {
        return rows;
    }
    /**
     * This method returns an object of the type integer whenever it is called.
     * @return how many columns that are in the auditorium
     */
    public int getCols() {
        return cols;
    }
}
