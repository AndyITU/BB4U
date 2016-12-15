package Model;
/**
 * This is the Auditorium class. It includes all necessary information about an auditorium for the "Arrival of Bookings" software.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class Auditorium {
    private int id, rows, cols;

    /**
     * The constructor gives Auditorium all parameters as private values. It checks these values for valid input.
     * @param id the id of the auditorium.
     * @param rows the amount of rows in the auditorium.
     * @param cols the amount of columns in the auditorium.
     */
    public Auditorium(int id, int rows, int cols) {
        if (id >= 0) this.id = id;
        else throw new IllegalArgumentException();
        if (rows > 0) this.rows = rows;
        else throw new IllegalArgumentException();
        if (cols > 0) this.cols = cols;
        else throw new IllegalArgumentException();
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
