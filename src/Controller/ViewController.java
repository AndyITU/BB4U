package Controller;

import Model.Reservation;
import Model.Show;
import View.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class ViewController {

    private static MainFrame frame;
    private final BookingViewPanel bookingViewPanel;
    private final SearchViewPanel searchPanel;
    private final ButtonPanel buttonPanel;
    private final ReservationPanel reservationViewPanel;
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
        reservationViewPanel = frame.getReservationPanel();
        setupButtons();
        bookingViewPanel.getSeatPanel().startBook();
    }
    private void setupButtons() {
        // Booking Panel book Button functionality
        bookingViewPanel.getInfoPanel().getBookButton().addActionListener(e -> {
            String customerName = bookingViewPanel.getInfoPanel().getCustomerName();
            String customerPhone = bookingViewPanel.getInfoPanel().getCustomerPhone();
            customerName.replace(" ","");
            customerPhone.replace(" ","");
            if ( !customerName.trim().equals("") && !customerPhone.trim().equals(""))
            {
                try {
                    Booking.makeReservation(new Reservation(
                            Database.getNextReservationID(),
                            currentShow.getId(),
                            bookingViewPanel.getSeatPanel().startBook(),
                            currentShow.getAud_id(),
                            customerName,
                            customerPhone));
                    //get contact info
                } catch (SQLException x) {
                    x.printStackTrace();
                } catch (IllegalArgumentException x) {
                    JOptionPane.showMessageDialog(null, x.getMessage());
                }
                bookingViewPanel.getInfoPanel().resetCustomerInfo();
                frame.getReservationPanel().updatePanels(Booking.getReservations());
            }
            else {
                JOptionPane.showMessageDialog(null, "You haven't entered a name or number");
            }
        });
        // Button Panels button functionality
        buttonPanel.getSearchButton().addActionListener(e -> frame.changeToPanel(searchPanel));
        buttonPanel.getBookingViewButton().addActionListener(e -> frame.changeToPanel(bookingViewPanel));
        buttonPanel.getReservationButton().addActionListener(e -> frame.changeToPanel(reservationViewPanel));

// SearchViewPanel setup

        ActionListener movieDropDown = e -> {
            JComboBox sendInput =(JComboBox) e.getSource();
            movieString =(String)sendInput.getSelectedItem();
            if (!Objects.equals(movieString, "")) {
                searchPanel.updateDate(Search.getDatesByMovie(movieString));
                searchPanel.getDateDropDown().setEnabled(true);
                searchPanel.getDateDropDown().setSelectedIndex(0);
            }
            else {
                searchPanel.getDateDropDown().setEnabled(false);
                searchPanel.getAuditoriumDropDown().setEnabled(false);
                searchPanel.getSelectShowButton().setEnabled(false);
            }
        };

        ActionListener dateDropDown = e -> {
            JComboBox sendInput =(JComboBox) e.getSource();
            dateString =(String) sendInput.getSelectedItem();
            if (!Objects.equals(movieString, "") && movieString != null && !Objects.equals(dateString, "") && dateString != null) {
                searchPanel.updateAud(Search.getAuditoriums(movieString, dateString));
                searchPanel.getAuditoriumDropDown().setEnabled(true);
                searchPanel.getAuditoriumDropDown().setSelectedIndex(0);
            }
            else {
                searchPanel.getAuditoriumDropDown().setEnabled(false);
                searchPanel.getSelectShowButton().setEnabled(false);
            }
        };

        ActionListener auditoriumDropDown = e -> {
            JComboBox sendInput =(JComboBox) e.getSource();
            auditoriumID = (String) sendInput.getSelectedItem();
            if (!Objects.equals(auditoriumID, "")) {
                searchPanel.getSelectShowButton().setEnabled(true);
            }
            else {
                searchPanel.getSelectShowButton().setEnabled(false);
            }
        };
        searchPanel.getMovieDropDown().addActionListener(movieDropDown);
        searchPanel.getDateDropDown().addActionListener(dateDropDown);
        searchPanel.getAuditoriumDropDown().addActionListener(auditoriumDropDown);

        searchPanel.getSelectShowButton().addActionListener(e -> {
            searchShow = Database.getShowFromSearch(movieString, auditoriumID, dateString);
            currentShow = searchShow;
            frame.updateMoviePanel(searchShow, Booking.getAuditorium(searchShow.getAud_id()), Booking.getReservedSeats(searchShow.getId()).length);
            setupButtons();
            frame.changeToPanel(bookingViewPanel);
            searchLockDown();
        });
    }

    public static String[] getMovieTitles() {
        return Search.getMovies();
    }

    public static void sendAnswer(int answer, Reservation r) {
        if (answer == JOptionPane.YES_OPTION) {
            System.out.println("Edit");
        }
            else if (answer == JOptionPane.NO_OPTION) {
                Booking.removeReservation(r);
                frame.getReservationPanel().updatePanels(Booking.getReservations());
            }
        }

    private void searchLockDown() {
        movieString = "";
        dateString = "";
        auditoriumID = "";
        searchPanel.getAuditoriumDropDown().setEnabled(false);
        searchPanel.getSelectShowButton().setEnabled(false);
        searchPanel.resetSearch();
    }
}
