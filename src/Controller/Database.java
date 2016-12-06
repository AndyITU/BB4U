package Controller;
import Model.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Database {
    // Very secret credentials
    private static final String DB = "jdbc:mysql://mydb.itu.dk/BB4U";
    private static final String USER = "g41admin";
    private static final String PASS = "progroup41";

    private static Connection connection;
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd. MMMM - yyyy HH:mm", new Locale("da", "DK"));


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
                    "INSERT INTO auditoriums (rows, cols) VALUES (5, 10);");
            statement.executeUpdate(
                    "INSERT INTO auditoriums (rows, cols) VALUES (6, 8);");
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

    static Show getShowFromSearch(String movie, String aud_id, String date) {
        Show show = new Show(0, 0, null, null, null);

        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();

            String q = "SELECT * FROM shows WHERE aud_id="+aud_id+" AND movie='"+movie+"' AND date='"+Timestamp.valueOf(LocalDateTime.parse(date, format))+"';";
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

    public static Auditorium[] getAuditoriums(int id) {
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

    static Reservation[] getReservations(int id, boolean show) {
        // If query fails, auditoriums should be returned as an empty array
        Reservation[] reservations = new Reservation[0];

        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();

            String q = id > 0 ? show ? " WHERE show_id="+id : " WHERE id="+id : "";
            ResultSet rs = statement.executeQuery("SELECT count(*) AS total FROM reservations"+q+";");
            rs.next();
            int total = rs.getInt("total");

            try {
                rs = statement.executeQuery("SELECT * FROM reservations"+q+";");
                if(show) {
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
                } else {
                    reservations = new Reservation[1];
                    SeatModel[] s = new SeatModel[total];
                    int show_id = 0, aud_id = 0;
                    String name = "", contact_info = "";
                    for(int i = 0; rs.next(); i++) {
                        if(i == 0) { show_id = rs.getInt("show_id"); aud_id = rs.getInt("aud_id"); name = rs.getString("name"); contact_info = rs.getString("contact_info");}
                        s[i] = new SeatModel(rs.getInt("col"), rs.getInt("row"), true);
                    }
                    reservations[0] = new Reservation(id, show_id, s, aud_id, name, contact_info);
                }
            } catch (SQLException e) {
                e.printStackTrace();
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

    static boolean isReserved(int show_id, SeatModel[] seats) {
        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet rs;

            rs = statement.executeQuery("SELECT * FROM reservations WHERE show_id=" + show_id + ";");
            while (rs.next()) {
                for (int i = 0; i < seats.length; i++) {
                    if (rs.getInt("col") == seats[i].getCol() && rs.getInt("row") == seats[i].getRow())
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
