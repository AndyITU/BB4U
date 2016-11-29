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

            System.out.println("Database initialized");
            System.out.println("Dropping tables...");
            statement.executeUpdate("DROP TABLE auditoriums, seats, shows;");
            System.out.println("Creating new tables...");

            statement.executeUpdate(
                    "CREATE TABLE shows (id int AUTO_INCREMENT PRIMARY KEY, aud_id int NOT NULL, movie VARCHAR(128) NOT NULL, date DATETIME NOT NULL, duration TIME NOT NULL);");
            statement.executeUpdate(
                    "CREATE TABLE auditoriums (id int AUTO_INCREMENT PRIMARY KEY, rows int, cols int);");
            statement.executeUpdate(
                    "CREATE TABLE seats (id int, row int, show_id int, taken boolean);");
            System.out.println("Inserting data...");
            statement.executeUpdate(
                    "INSERT INTO shows (aud_id, movie, date, duration) VALUES (1, 'Star Wars IV - A New Hope', '2016-11-27 21:30:00', '2:05');");
            statement.executeUpdate(
                    "INSERT INTO auditoriums (rows, cols) VALUES (5, 10);");
            System.out.println("Inserting seats...");
            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 10; j++) {
                    statement.executeUpdate("INSERT INTO seats VALUES("+i+", "+j+", 1, FALSE); ");
                }
            }
            System.out.println("Queries finished successfully. Closing connection...");
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /* GETTERS */

    public static Show[] getShows() {
        Show[] shows;

        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT count(*) AS total FROM shows;");
            rs.next();
            shows = new Show[rs.getInt("total")];
            try {
                rs = statement.executeQuery("SELECT * FROM shows;");
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

    static SeatModel[] getSeats(int show_id) {
        SeatModel[] seats;

        try {
            connection = DriverManager.getConnection(DB, USER, PASS);
            Statement statement = connection.createStatement();

            String q = show_id > 0 ? " WHERE show_id = "+show_id : "";
            ResultSet rs = statement.executeQuery("SELECT count(*) AS total FROM seats"+q+";");
            rs.next();
            seats = new SeatModel[rs.getInt("total")];
            try {
                rs = statement.executeQuery("SELECT * FROM seats;");
                int i = 0;
                while (rs.next()) {
                    seats[i] = new SeatModel(rs.getInt("id"), rs.getInt("row"), rs.getInt("show_id"), rs.getBoolean("taken"));
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch(SQLException e) {
            e.printStackTrace();
            // If query fails, auditoriums should be returned as an empty array
            seats = new SeatModel[0];
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return seats;
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
