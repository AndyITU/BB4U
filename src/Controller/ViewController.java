package Controller;

import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by arha on 12/3/2016.
 */
public class ViewController {
    private MainFrame frame;
    private BookingViewPanel bookingViewPanel;
    private SearchViewPanel searchPanel;
    private ButtonPanel buttonPanel;

    public ViewController(MainFrame frame) {
        this.frame = frame;
        bookingViewPanel = frame.getBookingPanel();
        searchPanel = frame.getSearchPanel();
        buttonPanel = frame.getButtonPanel();
        setupButtons();
    }
                /**
                 try {
                 // Set the seat to something plz.. Can't wait to test!
                 Booking.bookSeats(new Model.SeatModel[]{seat});
                 } catch(IllegalArgumentException | SQLException err) {
                 // Make a class for an error-message-window and use it here:
                 System.out.println(err);
                 }
                 */
    public boolean setMovie() {
        return true;
    }

    private void setupButtons() {

        bookingViewPanel.getInfoPanel().getMethodButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingViewPanel.getInfoPanel().setShowInfo("Something", 4);
            }
        });
        bookingViewPanel.getInfoPanel().getBookButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingViewPanel.getInfoPanel().getPanel().startBook();
            }
        });

        buttonPanel.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changeToPanel(searchPanel);

            }
        });
        buttonPanel.getBookingViewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changeToPanel(bookingViewPanel);
            }
        });
    }
}
