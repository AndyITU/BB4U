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
    private String movieString = "";
    private String dateString = "";
    private String auditoriumID = "";

    public ViewController() {
        frame = new MainFrame(currentShow, Booking.getAuditorium(currentShow.getAud_id()), Booking.getReservedSeats(currentShow.getAud_id()).length);
        bookingViewPanel = frame.getBookingPanel();
        searchPanel = frame.getSearchPanel();
        buttonPanel = frame.getButtonPanel();
        setupButtons();
        bookingViewPanel.getInfoPanel().getPanel().startBook();
        System.out.println(Booking.getShow(2));
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
        ItemListener movieDropDown = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox sendInput =(JComboBox) e.getSource();
                if (movieString != sendInput.getSelectedItem()) {
                    lockDown();
                }
                movieString =(String)sendInput.getSelectedItem();
                if (movieString != "") {
                    searchPanel.getDateDropDown().setEnabled(true);
                }
                else {
                    searchPanel.getDateDropDown().setEnabled(false);
                }
            }
        };

        ItemListener dateDropDown = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox sendInput =(JComboBox) e.getSource();
                dateString =(String) sendInput.getSelectedItem();
                if (dateString != "") {
                    searchPanel.getAuditoriumDropDown().setEnabled(true);
                }
                else {
                    searchPanel.getAuditoriumDropDown().setEnabled(false);
                    searchPanel.getSelectShowButton().setEnabled(false);
                }
            }
        };

        ItemListener auditoriumDropDown = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox sendInput =(JComboBox) e.getSource();
                auditoriumID = (String) sendInput.getSelectedItem();
                System.out.println(sendInput.getSelectedItem());
                if (auditoriumID !="" ) {
                    searchPanel.getSelectShowButton().setEnabled(true);
                }
                else {
                    searchPanel.getSelectShowButton().setEnabled(false);
                }
            }
        };
        searchPanel.getMovieDropDown().addItemListener(movieDropDown);
        searchPanel.getDateDropDown().addItemListener(dateDropDown);
        searchPanel.getAuditoriumDropDown().addItemListener(auditoriumDropDown);
        searchPanel.getSelectShowButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changeToPanel(bookingViewPanel);
                lockDown();
            }
        });
    }

    private void lockDown() {
        movieString = "";
        dateString = "";
        auditoriumID = "";
        searchPanel.getAuditoriumDropDown().setEnabled(false);
        searchPanel.getSelectShowButton().setEnabled(false);
        searchPanel.resetSearch();
    }
}
