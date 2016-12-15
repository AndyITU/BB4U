package Model;
/**
 * This is the Reservation class. It includes all necessary information about a reservation for the "Arrival of Bookings" software.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class Reservation {
    private int id, show_id, aud_id;
    private final String name, contact_info;
    private final SeatModel[] seats;

    /**
     * The constructor gives Reservation all parameters as private values. It checks some of these values for valid input.
     * @param id id of the reservation.
     * @param show_id id of the show connected to the reservation.
     * @param seats the collected seats connected to a single reservation.
     * @param aud_id id of the auditorium connected to the reservation.
     * @param name name of the customer that made a reservation.
     * @param contact_info phone number of the customer that made a reservation.
     */
    public Reservation(int id, int show_id, SeatModel[] seats, int aud_id, String name, String contact_info) {
        if (id >= 0) this.id = id;
        else throw new IllegalArgumentException();
        if (show_id >= 0) this.show_id = show_id;
        else throw new IllegalArgumentException();
        if (seats != null) this.seats = seats;
        else throw new IllegalArgumentException();
        if (aud_id >= 0) this.aud_id = aud_id;
        else throw new IllegalArgumentException();
        this.name = name;
        this.contact_info = contact_info;
    }
    /* GETTERS */
    /**
     * This method returns an object of the type integer whenever it is called.
     * @return id of reservation
     */
    public int getId() {
        return id;
    }
    /**
     * This method returns an object of the type integer whenever it is called.
     * @return id of the show that is currently in the reservation.
     */
    public int getShow_id() {
        return show_id;
    }
    /**
     * This method returns an object of the type integer whenever it is called.
     * @return id of the auditorium that is currently in the reservation.
     */
    public int getAud_id() {
        return aud_id;
    }
    /**
     * This method returns an object of the type String whenever it is called.
     * @return name of the customer that is currently in the reservation.
     */
    public String getName() {
        return name;
    }
    /**
     * This method returns an object of the type String whenever it is called.
     * @return contact_info (phone numer of the customer) that is currently in the reservation.
     */
    public String getContact_info() {
        return contact_info;
    }
    /**
     * This method returns an array of SeatModels whenever it is called.
     * @return seats that are currently connected to the reservation.
     */
    public SeatModel[] getSeats() {
        return seats;
    }
}
