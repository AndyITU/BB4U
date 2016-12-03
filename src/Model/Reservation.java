package Model;

public class Reservation {
    private int id, show_id, aud_id;
    private String name, contact_info;
    private SeatModel[] seats;


    public Reservation(int id, int show_id, SeatModel[] seats, int aud_id, String name, String contact_info) {
        this.id = id;
        this.show_id = show_id;
        this.seats = seats;
        this.aud_id = aud_id;
        this.name = name;
        this.contact_info = contact_info;
    }


    /* GETTERS */
    public int getId() {
        return id;
    }

    public int getShow_id() {
        return show_id;
    }

    public int getAud_id() {
        return aud_id;
    }

    public String getName() {
        return name;
    }

    public String getContact_info() {
        return contact_info;
    }

    public SeatModel[] getSeats() {
        return seats;
    }
}