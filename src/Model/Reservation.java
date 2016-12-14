package Model;
/**
 * Write a description of class BookingFrame here.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class Reservation {
    private final int id, show_id, aud_id;
    private final String name, contact_info;
    private final SeatModel[] seats;


    public Reservation(int id, int show_id, SeatModel[] seats, int aud_id, String name, String contact_info) {
        this.id = id;
        this.show_id = show_id;
        this.seats = seats;
        this.aud_id = aud_id;
        this.name = name;
        this.contact_info = contact_info;
    }
    /* GETTERS */
    /**
     * This method returns an object of the type boolean whenever it is called.
     * @return isBooked that is currently in SeatModel.
     */
    public int getId() {
        return id;
    }
    /**
     * This method returns an object of the type boolean whenever it is called.
     * @return isBooked that is currently in.
     */
    public int getShow_id() {
        return show_id;
    }
    /**
     * This method returns an object of the type integer whenever it is called.
     * @return isBooked that is currently in SeatModel.
     */
    public int getAud_id() {
        return aud_id;
    }
    /**
     * This method returns an object of the type String whenever it is called.
     * @return isBooked that is currently in SeatModel.
     */
    public String getName() {
        return name;
    }
    /**
     * This method returns an object of the type String whenever it is called.
     * @return contact_info that is currently in SeatModel.
     */
    public String getContact_info() {
        return contact_info;
    }
    /**
     * This method returns an object of the type String whenever it is called.
     * @return seats that is currently in SeatModel.
     */
    public SeatModel[] getSeats() {
        return seats;
    }
}
