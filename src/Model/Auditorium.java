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

    public int getId() {
        return id;
    }
    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
}
