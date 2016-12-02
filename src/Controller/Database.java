package Controller;
import Model.*;
import java.sql.*;

public class Database {
    // Very secret credentials
    private static final String DB = "jdbc:mysql://mydb.itu.dk/BB4U";
    private static final String USER = "g41admin";
    private static final String PASS = "progroup41";

    private static Connection connection;
    //private static Statement statement;

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
                    "CREATE TABLE reservations (id int AUTO_INCREMENT PRIMARY KEY, show_id int NOT NULL, row int NOT NULL, col int NOT NULL, aud_id int NOT NULL, name VARCHAR(128) NOT NULL, contact_info VARCHAR(128) UNIQUE);");
            System.out.println("*** SUCCESS ***\nInserting data...");
            statement.executeUpdate(
                    "INSERT INTO shows (aud_id, movie, date, duration) VALUES (1, 'Star Wars IV - A New Hope', '2016-11-27 21:30:00', '2:05');");
            statement.executeUpdate(
                    "INSERT INTO auditoriums (rows, cols) VALUES (5, 10);");
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

    public static Show[] getShows(int id) {
        Show[] shows;

        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();

            String q = id > 0 ? " WHERE show_id = "+id : "";
            ResultSet rs = statement.executeQuery("SELECT count(*) AS total FROM shows"+q+";");
            rs.next();
            shows = new Show[rs.getInt("total")];
            try {
                rs = statement.executeQuery("SELECT * FROM shows"+q+";");
                int i = 0;
                while (rs.next()) {
                    shows[i] = new Show(rs.getInt("id"), rs.getInt("aud_id"), rs.getString("movie"),
                            rs.getDate("date"), rs.getTime("Duration"));
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

    public static Auditorium[] getAuditoriums() {
        Auditorium[] auditoriums;

        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT count(*) AS total FROM auditoriums;");
            rs.next();
            auditoriums = new Auditorium[rs.getInt("total")];
            try {
                rs = statement.executeQuery("SELECT * FROM auditoriums;");
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

    static Reservation[] getReservations(int show_id) {
        Reservation[] reservations;

        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();

            String q = show_id > 0 ? " WHERE show_id = "+show_id : "";
            ResultSet rs = statement.executeQuery("SELECT count(*) AS total FROM reservations"+q+";");
            rs.next();
            reservations = new Reservation[rs.getInt("total")];
            try {
                rs = statement.executeQuery("SELECT * FROM reservations"+q+";");
                int i = 0;
                while (rs.next()) {
                    reservations[i] = new Reservation(rs.getInt("id"), rs.getInt("show_id"), rs.getInt("row"), rs.getInt("col"), rs.getInt("aud_id"), rs.getString("name"), rs.getString("contact_info"));
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch(SQLException e) {
            e.printStackTrace();
            // If query fails, auditoriums should be returned as an empty array
            reservations = new Reservation[0];
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reservations;
    }
    // getter without parameter
    static Reservation[] getReservations() { return getReservations(0); }

    static boolean isReserved(Reservation[] reservations) {
        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet rs;

            for(Reservation r: reservations) {
                rs = statement.executeQuery("SELECT * FROM reservations " +
                        "WHERE show_id="+r.getShow_id()+" AND row="+r.getRow()+" AND col="+r.getCol()+";");
                if(rs.next())
                    return true;
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
