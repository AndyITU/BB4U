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
    private Show currentShow = Booking.getShow(2);
    private Show searchShow;
    private String movieString = "";
    private String dateString = "";
    private String auditoriumID = "";

    public ViewController() {
        frame = new MainFrame(currentShow, Booking.getAuditorium(currentShow.getAud_id()), Booking.getReservedSeats(currentShow.getId()).length);
        bookingViewPanel = frame.getBookingPanel();
        searchPanel = frame.getSearchPanel();
        buttonPanel = frame.getButtonPanel();
        setupButtons();
        bookingViewPanel.getInfoPanel().getPanel().startBook();
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

        bookingViewPanel.getInfoPanel().getBookButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingViewPanel.getInfoPanel().getPanel().startBook();
                //get contact info
                //get movie info
                //Booking.bookSeats(Reservation r)
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

// Seachpanel setup

        ActionListener movieDropDown = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox sendInput =(JComboBox) e.getSource();
                if (movieString != sendInput.getSelectedItem()) {
                    System.out.println(movieString);
                }
                movieString =(String)sendInput.getSelectedItem();
                System.out.println(movieString);
                if (movieString != "") {
                    System.out.println(movieString);
                    searchPanel.updateDate(Search.getDatesByMovie(movieString));
                    searchPanel.getDateDropDown().setEnabled(true);
                    searchPanel.getDateDropDown().setSelectedIndex(0);
                }
                else {
                    searchPanel.getDateDropDown().setEnabled(false);
                    searchPanel.getAuditoriumDropDown().setEnabled(false);
                    searchPanel.getSelectShowButton().setEnabled(false);
                }
            }
        };

        ActionListener dateDropDown = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                JComboBox sendInput =(JComboBox) e.getSource();
                dateString =(String) sendInput.getSelectedItem();
                if (movieString != "" && movieString != null && dateString != "" && dateString != null) {
                    System.out.println("Strings: " + movieString + ", " + dateString);
                    searchPanel.updateAud(Search.getAuditoriums(movieString, dateString));
                    searchPanel.getAuditoriumDropDown().setEnabled(true);
                    searchPanel.getAuditoriumDropDown().setSelectedIndex(0);
                }
                else {
                    searchPanel.getAuditoriumDropDown().setEnabled(false);
                    searchPanel.getSelectShowButton().setEnabled(false);
                }
            }
        };

        ActionListener auditoriumDropDown = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                JComboBox sendInput =(JComboBox) e.getSource();
                auditoriumID = (String) sendInput.getSelectedItem();
                if (auditoriumID !="" ) {
                    searchPanel.getSelectShowButton().setEnabled(true);
                }
                else {
                    searchPanel.getSelectShowButton().setEnabled(false);
                }
            }
        };
        searchPanel.getMovieDropDown().addActionListener(movieDropDown);
        searchPanel.getDateDropDown().addActionListener(dateDropDown);
        searchPanel.getAuditoriumDropDown().addActionListener(auditoriumDropDown);

        searchPanel.getSelectShowButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchShow = Booking.getShow(1);
                System.out.println(searchShow.getMovie()+"1");
                frame.updateMoviePanel(searchShow, Booking.getAuditorium(searchShow.getAud_id()), Booking.getReservedSeats(searchShow.getId()).length);
                frame.changeToPanel(bookingViewPanel);
                lockDown();
            }
        });
    }

    public static String[] getMovieTitles() {
        return Search.getMovies();
    }

    public static String[] getDateTitles(String movie) {
        return Search.getDatesByMovie(movie);
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
