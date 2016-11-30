package Model;

public class SeatModel {
    private int col, row, show_id;
    private boolean isBooked;

    public SeatModel(int col, int row, int show_id, boolean isBooked) {
        this.col = col;
        this.row = row;
        this.show_id = show_id;
        this.isBooked = isBooked;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getShow_id() {
        return show_id;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        if(isBooked != booked)
            isBooked = booked;
    }
}
