package Controller;
import Model.*;
import java.sql.*;

public class Booking {

    public static SeatModel[] getSeats(int show_id) {
        return Controller.Database.getSeats(show_id);
    }

    static void bookSeats(SeatModel[] seats) throws SQLException, IllegalArgumentException {
        for(SeatModel s: seats)
            if(s.isBooked())
                throw new IllegalArgumentException("One or more of the seats are already booked!");

        try {
            for(SeatModel s: seats) {
                Database.updateTable(
                        "UPDATE seats SET taken=true WHERE id=" + s.getId() +
                        " AND aud_id=" + s.getAud_id() + " AND row=" + s.getRow() + ";"
                );
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}