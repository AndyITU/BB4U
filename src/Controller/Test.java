package Controller;
import Model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@SuppressWarnings("EmptyMethod")
class Test {
//    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd. MMMM - yyyy HH:mm", new Locale("da", "DK"));

    public static void main(String[] args) {
        // Test getReservedSeats
        /*for(SeatModel s: Booking.getReservedSeats(1))
            System.out.println(s.getCol());*/

        // Test for empty table
        //System.out.println(Booking.getReservedSeats(1).length);

        // Test for booking seats
        /*SeatModel[] s = new SeatModel[]{
            new SeatModel(1, 3, true),
            new SeatModel(2, 3, true),
            new SeatModel(3, 3, true),
            new SeatModel(4, 3, true)
        };
        try {
            Booking.bookSeats(new Reservation(Database.getNextReservationID(), 2, s, 1, "Andy", "12345678"));
        } catch(SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }*/

        // Test for getReservation
        /*for(Reservation r: Booking.getReservations()) {
            System.out.println();
            for(SeatModel s: r.getSeats()){
                System.out.println("Show: "+r.getShow_id()+", Col: "+s.getCol()+", Row: "+s.getRow());
            }
        }*/

        // Test for getShow (by movie)
        //System.out.println("Movie: " + Booking.getShow(1).getMovie());

        // Test for getMovies
        /*for(String movie: Search.getMovies())
            System.out.println("Movie: " + movie);*/

        // Test for getDates
        /*for(String date: Search.getDates())
            System.out.println("Date: " + date);*/

        // Test for formatting and converting Timestamp/LocalDateTime
        //System.out.println("Test: "+ Timestamp.valueOf(LocalDateTime.parse("27. november - 2016 21:30", format)).toString());
    }
}
