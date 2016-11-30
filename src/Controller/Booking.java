package Controller;
import Model.*;
import java.sql.*;

public class Booking {

    public static SeatModel[] getSeats(int show_id) {
        Reservation[] r = Database.getReservations(show_id);
        SeatModel[] s = new SeatModel[r.length];

        for(int i = 0; i < r.length; i++)
            s[i] = new SeatModel(r[i].getCol(), r[i].getRow(), r[i].getShow_id(), true);

        return s;
    }

    static void bookSeats(SeatModel[] seats) throws /*SQLException,*/IllegalArgumentException {
        for(SeatModel s: seats)
            if(s.isBooked())
                throw new IllegalArgumentException("One or more of the seats are already booked!");

        /*try {
            for(SeatModel s: seats) {
                Database.updateTable(
                        "UPDATE seats SET taken=true WHERE id=" + s.getCol() +
                        " AND aud_id=" + s.getShow_id() + " AND row=" + s.getRow() + ";"
                );
            }
        } catch (SQLException e) {
            throw e;
        }*/
    }
}