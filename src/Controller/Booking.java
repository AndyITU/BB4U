package Controller;
import Model.*;

import javax.swing.*;
import java.sql.SQLException;
/**
 * Write a description of class BookingFrame here.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class Booking {
    private static Show show;
    private static Auditorium auditorium;


    /* GETTERS */
    public static Show getShow(int id) {
        if(show == null || show.getId() != id)
            show = Database.getShows(id)[0];
        return show;
    }
    /*public static Show[] getShows(){
        return Database.getShows(0);
    }*/

    static Auditorium getAuditorium(int id) {
        if(auditorium == null || auditorium.getId() != id)
            auditorium = Database.getAuditoriums(id)[0];
        return auditorium;
    }


    public static SeatModel[] getReservedSeats(int show_id) {
        Reservation[] r = Database.getReservations(show_id);
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

    public static Reservation[] getReservations(String name, String contact_info) {
        return Database.getReservations(name, contact_info);
    }
    /*static Reservation[] getReservations(int show_id) {
        return Database.getReservations(show_id);
    }*/
    public static Reservation[] getReservations() {
        return Database.getReservations(0);
    }


    /* SETTERS */

    static void makeReservation(Reservation r) throws SQLException, IllegalArgumentException {
        if(Database.isReserved(r.getShow_id(), r.getSeats()))
            throw new IllegalArgumentException("One or more of the seats are already booked!");
        if(r.getName().equals("") || r.getContact_info().equals(""))
            throw new IllegalArgumentException("One or more of the text fields are empty!");
        if(r.getSeats().length == 0) {
            throw new IllegalArgumentException("You haven't chosen any seats!");
        }

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

    static void removeReservation(Reservation r) {
        try {
            Database.updateTable(
                    "DELETE FROM reservations WHERE id="+r.getId()
            );
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    static void editReservation(Reservation r, Reservation r1) {
        removeReservation(r);
        try {
            makeReservation(r1);
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            try {
                makeReservation(r);
            } catch(SQLException | IllegalArgumentException x) {
                x.printStackTrace();
            }
        }
    }
}