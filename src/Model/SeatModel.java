package Model;

public class SeatModel {
    private int id, row, aud_id;
    private boolean isBooked;

    public SeatModel(int id, int row, int aud_id, boolean isBooked) {
        this.id = id;
        this.row = row;
        this.aud_id = aud_id;
        this.isBooked = isBooked;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getAud_id() {
        return aud_id;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        if(isBooked != booked)
            isBooked = booked;
    }
}
