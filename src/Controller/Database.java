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
 * Made with mysql-connecter-java version 5.1.39
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

    /**
     * Resets the database tables and inserts some movies and auditoriums for the purpose of testing.
     * This method WILL throw SQLExceptions if the database does not have the right tables beforehand.
     *
     * @param args Unused parameter
     */
    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();

            System.out.println("Database initialized\n");
            System.out.println("Dropping tables...");
            statement.executeUpdate("DROP TABLE auditoriums, reservations, shows;");
            System.out.println("*** SUCCESS ***\nCreating new tables...");

            statement.executeUpdate(
                    "CREATE TABLE shows (id int AUTO_INCREMENT PRIMARY KEY, aud_id int NOT NULL, movie VARCHAR(128) NOT NULL, date DATETIME NOT NULL, duration TIME NOT NULL);");
            statement.executeUpdate(
                    "CREATE TABLE auditoriums (id int AUTO_INCREMENT PRIMARY KEY, rows int NOT NULL, cols int NOT NULL);");
            statement.executeUpdate(
                    "CREATE TABLE reservations (id int NOT NULL, show_id int NOT NULL, row int NOT NULL, col int NOT NULL, aud_id int NOT NULL, name VARCHAR(128) NOT NULL, contact_info VARCHAR(128) NOT NULL);");
            System.out.println("*** SUCCESS ***\nInserting data...");
            statement.executeUpdate(
                    "INSERT INTO shows (aud_id, movie, date, duration) VALUES (1, 'Star Wars IV - A New Hope', '2016-11-27 21:30:00', '2:01');");
            statement.executeUpdate(
                    "INSERT INTO shows (aud_id, movie, date, duration) VALUES (2, 'Star Wars V - The Empire Strikes Back', '2016-11-28 22:30:00', '2:04');");
            statement.executeUpdate(
                    "INSERT INTO shows (aud_id, movie, date, duration) VALUES (3, 'The Lord of The Rings: The Fellowship of the Ring 3D', '2016-11-29 23:30:00', '3:48');");
            statement.executeUpdate(
                    "INSERT INTO shows (aud_id, movie, date, duration) VALUES (4, 'The Lord of The Rings: The Fellowship of the Ring 3D', '2016-11-29 23:30:00', '3:48');");
            statement.executeUpdate(
                    "INSERT INTO shows (aud_id, movie, date, duration) VALUES (5, 'The Hobbit: Desolation of Smaug', '2016-11-30 21:00:00', '2:41');");
            statement.executeUpdate(
                    "INSERT INTO auditoriums (rows, cols) VALUES (5, 10);");
            statement.executeUpdate(
                    "INSERT INTO auditoriums (rows, cols) VALUES (10, 15);");
            statement.executeUpdate(
                    "INSERT INTO auditoriums (rows, cols) VALUES (6, 10);");
            statement.executeUpdate(
                    "INSERT INTO auditoriums (rows, cols) VALUES (6, 9 );");
            statement.executeUpdate(
                    "INSERT INTO auditoriums (rows, cols) VALUES (5,9);");
            System.out.println("*** SUCCESS ***\n\nQueries finished successfully\nClosing connection...");
        } catch(SQLException e) {
            System.out.println("*** FAILURE ***");
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                System.out.println("Connection successfully closed");
            } catch (SQLException e) {
                System.out.println("WARNING: Unable to close connection!");
                e.printStackTrace();
            }
        }
    }


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
     *
     * @param movie
     * @param aud_id
     * @param date
     * @return
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
     *
     * @param id
     * @return
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
                seatsHashMap.get(rs.getInt("id")).add(new SeatModel(rs.getInt("col"), rs.getInt("row"), true));
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
     *
     * @param show_id
     * @return
     */
    static Reservation[] getReservations(int show_id) {
        return getReservations(new String[]{Integer.toString(show_id)});
    }

    /**
     * @param name Name to be stored in database
     * @param contact_info Contact information to be stored in database
     * @return
     */
    static Reservation[] getReservations(String name, String contact_info) {
        return getReservations(new String[]{name, contact_info});
    }

    /**
     *
     * @param show_id
     * @param seats
     * @return
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
     *
     * @return
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
     *
     *
     * @param q
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
