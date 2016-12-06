package Controller;
import Model.*;

import java.sql.SQLException;

public class Booking {
    private static Show show;
    private static Auditorium auditorium;


    /* GETTERS */
    static Show getShow(int id) {
        if(show == null || show.getId() == id)
            show = Database.getShows(id)[0];
        System.out.println(show.getId() + " & " + show.getMovie());
        return show;
    }
    public static Show[] getShows(){
        return Database.getShows(0);
    }

    static Auditorium getAuditorium(int id) {
        if(auditorium == null || auditorium.getId() == id)
            auditorium = Database.getAuditoriums(id)[0];
        return auditorium;
    }


    public static SeatModel[] getReservedSeats(int show_id) {
        Reservation[] r = Database.getReservations(show_id, true);
        int amount = 0;

        for(Reservation res: r)
            amount += res.getSeats().length;
        SeatModel[] s = new SeatModel[amount];
        amount = 0;
        for(Reservation res: r)
            for(SeatModel s1: res.getSeats()) {
                s[amount] = s1;
                amount++;
            }

        return s;
    }
    static Reservation[] getReservations(int show_id) {
        return Database.getReservations(show_id, true);
    }
    static Reservation[] getReservations() {
        return getReservations(0);
    }
    static Reservation getReservation(int id) {
        return Database.getReservations(id, false)[0];
    }


    /* SETTERS */

    public static void bookSeats(Reservation r) throws SQLException, IllegalArgumentException {
        if(Database.isReserved(r.getShow_id(), r.getSeats()))
            throw new IllegalArgumentException("One or more of the seats are already booked!");

        try {

            for(SeatModel s: r.getSeats()) {
                Database.updateTable(
                        "INSERT INTO reservations (id, show_id, row, col, aud_id, name, contact_info) " +
                        "VALUES("+r.getId()+", "+r.getShow_id()+", "+s.getRow()+", "+s.getCol()+", " +
                        r.getAud_id()+", '"+r.getName()+"', '"+r.getContact_info()+"');"
                );
            }
        } catch (SQLException e) {
            throw e;
        }
    }

}