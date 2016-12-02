package Controller;
import Model.*;
import java.sql.SQLException;

public class Booking {
    private int show_id;
    private Show show;


    /* GETTERS */

    public Show getShow(int id) {
        if(show_id == id)
            return show;
        else {
            show_id = id;
            show = Database.getShows(id)[0];
            return show;
        }
    }

    public static SeatModel[] getSeats(int show_id) {
        Reservation[] r = Database.getReservations(show_id);
        SeatModel[] s = new SeatModel[r.length];

        for(int i = 0; i < r.length; i++)
            s[i] = new SeatModel(r[i].getCol(), r[i].getRow(), r[i].getShow_id(), true);

        return s;
    }


    /* SETTERS */

    public static void bookSeats(Reservation[] reservations) throws SQLException, IllegalArgumentException {
        for(Reservation r: reservations)
            if(r.getId() > 0)
                throw new IllegalArgumentException("One or more of the seats are already booked!");

        try {
            for(Reservation r: reservations) {
                Database.updateTable(
                        "INSERT INTO reservations (show_id, row, col, aud_id, name, contact_info) " +
                        "VALUES(" + r.getShow_id() + ", " + r.getRow() + " " + r.getRow() + ";"
                );
            }
        } catch (SQLException e) {
            throw e;
        }
    }


}