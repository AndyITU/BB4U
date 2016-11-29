/*

public class Booking {

    public static View.Seat[] getSeats(int show_id) {
        return Database.getSeats(show_id);
    }

    static void bookSeats(View.Seat[] seats) throws SQLException, IllegalArgumentException {
        for(View.Seat s: seats)
            if(s.isBooked())
                throw new IllegalArgumentException("One or more of the seats are already booked!");

        try {
            for(View.Seat s: seats) {
                Database.updateTable(
                        "UPDATE seats SET taken=true WHERE id=" + s.getId() +
                        " AND aud_id=" + s.getAud_id() + " AND row=" + s.getRow() + ";"
                );
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}
*/