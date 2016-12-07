package Model;

public class SeatModel {
    private final int col, row;
    private final boolean isBooked;

    public SeatModel(int col, int row, boolean isBooked) {
        this.col = col;
        this.row = row;
        this.isBooked = isBooked;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean getIsBooked() {
        return isBooked;
    }

    /*public void setBooked(boolean booked) {
        if(isBooked != booked)
            isBooked = booked;
    }*/
}