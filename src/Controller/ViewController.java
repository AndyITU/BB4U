package Controller;

import Model.Show;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by arha on 12/3/2016.
 */
public class ViewController {
    private MainFrame frame;
    private BookingViewPanel bookingViewPanel;
    private SearchViewPanel searchPanel;
    private ButtonPanel buttonPanel;
    private Show currentShow = Booking.getShow(1);
    private Show searchShow;

    public ViewController() {
        frame = new MainFrame(currentShow, Booking.getAuditorium(currentShow.getAud_id()), Booking.getReservedSeats(currentShow.getAud_id()).length);
        bookingViewPanel = frame.getBookingPanel();
        searchPanel = frame.getSearchPanel();
        buttonPanel = frame.getButtonPanel();
        setupButtons();
        bookingViewPanel.getInfoPanel().getPanel().startBook();
        System.out.println(Booking.getShow(2).getMovie());
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

        // SearchPanel dropdown setup.

        searchPanel.getMovieDropDown().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox sendInput =(JComboBox) e.getSource();
                sendInput.getSelectedItem();
            }
        });
        searchPanel.getDateDropDown().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox sendInput =(JComboBox) e.getSource();
                sendInput.getSelectedItem();
                System.out.println(sendInput.getSelectedItem());

            }
        });

        searchPanel.getAuditoriumDropDown().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox sendInput =(JComboBox) e.getSource();
                sendInput.getSelectedItem();
                System.out.println(sendInput.getSelectedItem());
            }
        });

        searchPanel.getSelectShowButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changeToPanel(bookingViewPanel);
            }
        });
    }
}
