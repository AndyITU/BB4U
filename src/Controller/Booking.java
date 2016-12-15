package Controller;
import Model.*;

import javax.swing.*;
import java.sql.SQLException;
/**
 * All booking-related functionality should be in this class.
 * Works as middleware for the Database-controller.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class Booking {
    private static Show show;
    private static Auditorium auditorium;


    /* GETTERS */

    /**
     * Returns a single show of the given id from the database.
     *
     * @param id An id of a show
     * @return A show of the given id
     */
    public static Show getShow(int id) {
        if(show == null || show.getId() != id)
            show = Database.getShows(id)[0];
        return show;
    }

    /**
     * Returns a single auditorium of the given id from the database.
     *
     * @param id An id of an auditorium
     * @return An auditorium of the given id
     */
    static Auditorium getAuditorium(int id) {
        if(auditorium == null || auditorium.getId() != id)
            auditorium = Database.getAuditoriums(id)[0];
        return auditorium;
    }

    /**
     * Returns an array of the object SeatModel containing all reserved seats from the database.
     *
     * @param show_id The id of a valid show
     * @return An array of reserved seats in the given show
     */
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

    /**
     * Unused method. Can be used for retrieving a reservation using name and contact information.
     * Optimal for searching for reservations in {@link View.ReservationPanel}.
     *
     * @param name Customer name
     * @param contact_info Customer contact information
     * @return An array of all reservations by a customer
     */
    static Reservation[] getReservations(String name, String contact_info) {
        return Database.getReservations(name, contact_info);
    }

    /**
     * Retrieves all reservations in the Database.
     *
     * @return An array containing all reservations
     */
    public static Reservation[] getReservations() {
        return Database.getReservations(0);
    }


    /* SETTERS */

    /**
     * Inserts a reservation into the database using given Reservation-object.
     *
     * @param r The reservation to be sent to the database
     * @throws SQLException If there's an error in the query to the database
     * @throws IllegalArgumentException If given a reservation with no seats, with seats that are already booked or with empty name/contact information
     */
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

    /**
     * Removes a reservation from the database.
     *
     * @param r The reservation to be removed
     */
    static void removeReservation(Reservation r) {
        try {
            Database.updateTable(
                    "DELETE FROM reservations WHERE id="+r.getId()
            );
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Replaces a reservation from the database.
     *
     * @param r The reservation to be replaced
     * @param r1 The new reservation to replace the old one
     */
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