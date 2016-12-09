package View;

import Model.SeatModel;

/**
 * Created by Mikkel on 09-12-2016.
 */
public interface SeatPanel_Interface {
    SeatModel[] startBook();
    void newBook(SeatModel[] seats);
}
