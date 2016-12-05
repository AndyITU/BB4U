package Controller;
import Model.*;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        // Test getReservedSeats
        for(SeatModel s: Booking.getReservedSeats(1))
            System.out.println(s.getCol());

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
        //System.out.println("Movie: " + Booking.getShow(2).getMovie());
    }
}
