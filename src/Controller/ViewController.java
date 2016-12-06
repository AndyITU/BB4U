package Controller;

import Model.Reservation;
import Model.Show;
import View.BookingViewPanel;
import View.ButtonPanel;
import View.MainFrame;
import View.SearchViewPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
        frame = new MainFrame(currentShow, Booking.getAuditorium(currentShow.getAud_id()), Booking.getReservedSeats(currentShow.getId()).length);
        bookingViewPanel = frame.getBookingPanel();
        searchPanel = frame.getSearchPanel();
        buttonPanel = frame.getButtonPanel();
        setupButtons();
        bookingViewPanel.getInfoPanel().getPanel().startBook();
    }
    private void setupButtons() {

        bookingViewPanel.getInfoPanel().getBookButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Booking.bookSeats(new Reservation(
                            Database.getNextReservationID(),
                            currentShow.getId(),
                            bookingViewPanel.getInfoPanel().getPanel().startBook(),
                            currentShow.getAud_id(),
                            "Mikkel",
                            "Din mor"));
                    //get contact info
                } catch (SQLException | IllegalArgumentException k) {
                    k.printStackTrace();
                }
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

// SearchViewPanel setup

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
                searchShow = Database.getShowFromSearch(movieString, auditoriumID, dateString);
                frame.updateMoviePanel(searchShow, Booking.getAuditorium(searchShow.getAud_id()), Booking.getReservedSeats(searchShow.getId()).length);
                setupButtons();
                frame.changeToPanel(bookingViewPanel);
                lockDown();
            }
        });
    }

    public static String[] getMovieTitles() {
        return Search.getMovies();
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
