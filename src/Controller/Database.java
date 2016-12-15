package Controller;

import Model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * In this this, you'll find all the actual calls to the Database.
 * All methods and fields are static.
 * Made with mysql-connector-java version 5.1.39
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
class Database {
    // Very secret database credentials
    private static final String DB = "jdbc:mysql://mydb.itu.dk/BB4U";
    private static final String USER = "g41admin";
    private static final String PASS = "progroup41";

    private static Connection connection;
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd. MMMM - yyyy HH:mm", new Locale("da", "DK"));


    /* GETTERS */

    /**
     * This method returns an array of objects of the type Show.
     * Given 0 (or anything below) the array contains all shows in the Database.
     * Otherwise it returns the show with the given id.
     *
     * @param id Either an integer less than 1 or the id of a show
     * @return An array of shows or an array containing a single show
     */
    static Show[] getShows(int id) {
        Show[] shows;

        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();

            String q = id > 0 ? " WHERE id="+id : "";
            ResultSet rs = statement.executeQuery("SELECT count(*) AS total FROM shows"+q+";");
            rs.next();
            shows = new Show[rs.getInt("total")];
            try {
                rs = statement.executeQuery("SELECT * FROM shows"+q+";");
                int i = 0;
                while (rs.next()) {
                    shows[i] = new Show(rs.getInt("id"), rs.getInt("aud_id"), rs.getString("movie"),
                            rs.getTimestamp("date").toLocalDateTime(), rs.getTime("Duration").toLocalTime());
                    i++;
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } catch(SQLException e) {
            e.printStackTrace();
            shows = new Show[0];
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return shows;
    }

    /**
     * Searches the Database for the given show information.
     *
     * @param movie title of the movie
     * @param aud_id the auditorium id
     * @param date the date the movie is playing
     * @return a show corresponding to the given parameters
     */
    static Show getShowFromSearch(String movie, String aud_id, String date) {
        Show show = new Show(0, 0, null, null, null);

        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();
            System.out.println(date);
            System.out.println(LocalDateTime.parse(date, format));
            String q = "SELECT * FROM shows WHERE aud_id="+aud_id+" AND movie='"+movie+"' AND date='"+Timestamp.valueOf(LocalDateTime.parse(date.trim(), format)) +"';";
            ResultSet rs = statement.executeQuery(q);
            rs.next();
            show = new Show(rs.getInt("id"), rs.getInt("aud_id"), rs.getString("movie"),
                    rs.getTimestamp("date").toLocalDateTime(), rs.getTime("Duration").toLocalTime());

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return show;
    }

    /**
     * This method returns an array of objects of the type Auditorium.
     * Given 0 (or anything below) the array contains all auditoriums in the Database.
     * Otherwise it returns the auditorium with the given id.
     *
     * @param id Either an integer less than 1 or the id of an auditorium
     * @return An array of either one or all auditorium
     */
    static Auditorium[] getAuditoriums(int id) {
        Auditorium[] auditoriums;

        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();

            String q = id > 0 ? " WHERE id = "+id : "";
            ResultSet rs = statement.executeQuery("SELECT count(*) AS total FROM auditoriums"+q+";");
            rs.next();
            auditoriums = new Auditorium[rs.getInt("total")];
            try {
                rs = statement.executeQuery("SELECT * FROM auditoriums"+q+";");
                int i = 0;
                while (rs.next()) {
                    auditoriums[i] = new Auditorium(rs.getInt("id"), rs.getInt("rows"), rs.getInt("cols"));
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch(SQLException e) {
            e.printStackTrace();
            // If query fails, auditoriums should be returned as an empty array
            auditoriums = new Auditorium[0];
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return auditoriums;
    }

    /**
     * This method returns an array of objects of the type Reservation.
     * Given an integer below 0 (or any integer below) the array contains all shows in the Database.
     * Otherwise it returns the reservation with the given id or the given name and contact information.
     *
     * @param args Either an integer less than 1, the id of a reservation or two strings containing name and contact information
     * @return An array of reservations or an array containing a single reservation
     */
    private static Reservation[] getReservations(String[] args) {
        int id = 0;
        String name = "", contact_info = "";
        if(args.length == 1)
            id = Integer.parseInt(args[0]);
        else {
            name = args[0]; contact_info = args[1];
        }

        // If query fails, auditoriums should be returned as an empty array
        Reservation[] reservations = new Reservation[0];

        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();

            String q = args.length == 1 ? id > 0 ? " WHERE show_id="+id : "" : " WHERE name='"+name+"' AND contact_info='"+contact_info+"'";
            ResultSet rs = statement.executeQuery("SELECT count(*) AS total FROM reservations"+q+";");
            rs.next();
            int total = rs.getInt("total");

            rs = statement.executeQuery("SELECT * FROM reservations"+q+";");

            // Using HashMap to group reservations in database by ID
            HashMap<Integer, List<SeatModel>> seatsHashMap = new HashMap<>();
            int[] ids = new int[total], shows = new int[total], auds = new int[total];
            String[] names = new String[total], contacts = new String[total];
            total = 0;
            while(rs.next()) {
                if(!seatsHashMap.containsKey(rs.getInt("id"))) {
                    seatsHashMap.put(rs.getInt("id"), new ArrayList<>());
                    ids[total] = rs.getInt("id"); auds[total] = rs.getInt("aud_id"); if(id<1) shows[total] = rs.getInt("show_id");
                    names[total] = rs.getString("name"); contacts[total] = rs.getString("contact_info");
                    total++;
                }
                seatsHashMap.get(rs.getInt("id")).add(new SeatModel(rs.getInt("col"), rs.getInt("row")));
            }
            reservations = new Reservation[total];
            for(int i = 0; i < total; i++) {
                reservations[i] = new Reservation(ids[i], id == 0 ? shows[i] : id,
                        seatsHashMap.get(ids[i]).toArray(new SeatModel[0]),
                        auds[i], names[i], contacts[i]);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reservations;
    }

    /**
     * Retrieves all reservations for a single or all shows using {@link #getReservations(String[])}.
     *
     * @param show_id Either an integer less than one for all shows or the id of a show
     * @return An array of reservations
     */
    static Reservation[] getReservations(int show_id) {
        return getReservations(new String[]{Integer.toString(show_id)});
    }

    /**
     * Can be used for retrieving a reservation using name and contact information.
     * Optimal for searching for reservations in {@link View.ReservationPanel}.
     *
     * @param name Customer name
     * @param contact_info Customer contact information
     * @return An array of all reservations by a customer
     */
    static Reservation[] getReservations(String name, String contact_info) {
        return getReservations(new String[]{name, contact_info});
    }

    /**
     * Checks whether or not the given seats are reserved for the given show.
     *
     * @param show_id The show to search in
     * @param seats The seats to be checked
     * @return True of any of the seats are reserved, false if none of the seats are reserved
     */
    static boolean isReserved(int show_id, SeatModel[] seats) {
        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet rs;

            rs = statement.executeQuery("SELECT * FROM reservations WHERE show_id=" + show_id + ";");
            while (rs.next()) {
                for (SeatModel seat: seats) {
                    if (rs.getInt("col") == seat.getCol() && rs.getInt("row") == seat.getRow())
                        return true;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return true;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the next reservation id from the database to be used for a new reservation.
     *
     * @return The next reservation id
     */
    static int getNextReservationID() {
        int id = 1;
        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet rs;

            rs = statement.executeQuery("SELECT MAX(id) AS id FROM reservations;");
            rs.next();
            id = rs.getInt("id")+1;
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }


    /* SETTERS */

    /**
     * Executes the given query if it's an update-query.
     *
     * @param q The query to be executed
     * @throws SQLException If there is an error in the given query
     */
    static void updateTable(String q) throws SQLException {
        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();

            statement.executeUpdate(q);
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }
}
