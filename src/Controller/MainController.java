package Controller;

import Model.Reservation;
import Model.Show;
import View.*;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Objects;
/**
 * Write a description of class BookingFrame here.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class MainController {

    private final MainFrame frame;
    private final BookingViewPanel bookingViewPanel;
    private final SearchViewPanel searchPanel;
    private final ButtonPanel buttonPanel;
    private final ReservationPanel reservationViewPanel;
    private Show currentShow = Booking.getShow(1);
    private Show searchShow;
    private Reservation editReservation;
    private String movieString = "";
    private String dateString = "";
    private String auditoriumID = "";

    private static MainController instance = null;

    public static void main (String[] args) {
        createInstance();

    }

    /**
     * Constructs an instance of the ViewController-class (singleton)
     */
    private MainController() {
        frame = new MainFrame(currentShow, Booking.getAuditorium(currentShow.getAud_id()), Booking.getReservedSeats(currentShow.getId()).length);
        bookingViewPanel = frame.getBookingPanel();
        searchPanel = frame.getSearchPanel();
        buttonPanel = frame.getButtonPanel();
        reservationViewPanel = frame.getReservationPanel();
        setupButtons();
    }

    /**
     * Creates an instance of the ViewController-class if no instance exists already (singleton)
     */
    static void createInstance() {
        if (instance == null) {
            instance = new MainController();
        }
    }

    /**
     * Returns the instance created by {@link #createInstance()}
     * @return an instance of the ViewController-class (singleton)
     */
    public static MainController getInstance() {
        return instance;
    }
    private void setupButtons() {
        // Booking Panel book Button functionality
        ActionListener bookingButton = e -> {
            String customerName = bookingViewPanel.getInfoPanel().getCustomerName();
            String customerPhone = bookingViewPanel.getInfoPanel().getCustomerPhone();
            if ( !customerName.trim().equals("") && customerPhone.trim().length() == 8 && customerName.trim().length() <= 35)
            {
                try {
                    Reservation r = new Reservation(Database.getNextReservationID(),
                            currentShow.getId(),
                            bookingViewPanel.getSeatPanel().newBooking(),
                            currentShow.getAud_id(),
                            customerName,
                            customerPhone);
                    Booking.makeReservation(r);
                    bookingViewPanel.getInfoPanel().updateSeatInfo(r.getSeats().length, 0);
                    //get contact info
                } catch (SQLException x) {
                    x.printStackTrace();
                } catch (IllegalArgumentException x) {
                    JOptionPane.showMessageDialog(null, x.getMessage());
                }
                bookingViewPanel.getInfoPanel().resetCustomerInfo();
                reservationViewPanel.updatePanels(Booking.getReservations(), currentShow.getId(), Booking.getAuditorium(currentShow.getId()));
            }
            else {
                JOptionPane.showMessageDialog(null, "There is an error in the customer info");
            }

        };
        bookingViewPanel.getInfoPanel().getBookButton().addActionListener(bookingButton);
        // Reservation seat panels functionality
        frame.setFocusable(true);

        // Button Panels button functionality
        buttonPanel.getSearchButton().addActionListener(e -> {
            frame.changeToPanel(searchPanel);
            searchPanel.updateInfo(getMovieTitles(),searchPanel.getMovieDropDown());
                }
        );
        buttonPanel.getBookingViewButton().addActionListener(e -> frame.changeToPanel(bookingViewPanel));
        buttonPanel.getReservationButton().addActionListener(e -> frame.changeToPanel(reservationViewPanel));

        // SearchViewPanel setup
        ActionListener movieDropDown = e -> {
            JComboBox sendInput =(JComboBox) e.getSource();
            movieString =(String)sendInput.getSelectedItem();
            if (!Objects.equals(movieString, "")) {
                if(movieString != null){
                searchPanel.updateInfo(Search.getDates(movieString),searchPanel.getDateDropDown());
                searchPanel.getDateDropDown().setEnabled(true);
                searchPanel.getDateDropDown().setSelectedIndex(0);}
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
                searchPanel.updateInfo(Search.getAuditoriums(movieString, dateString),searchPanel.getAuditoriumDropDown());
                searchPanel.getAuditoriumDropDown().setEnabled(true);
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
            System.out.println("Strings: "+movieString+", "+auditoriumID+", "+dateString);
            searchShow = Database.getShowFromSearch(movieString, auditoriumID, dateString);
            currentShow = searchShow;
            bookingViewPanel.updatePanels(searchShow, Booking.getAuditorium(searchShow.getAud_id()), Booking.getReservedSeats(searchShow.getId()).length);
            reservationViewPanel.updatePanels(Booking.getReservations(), currentShow.getId(), Booking.getAuditorium(currentShow.getAud_id()));
            frame.changeToPanel(bookingViewPanel);
            bookingViewPanel.getInfoPanel().getBookButton().addActionListener(bookingButton);
            movieString = "";
            dateString = "";
            auditoriumID = "";
            searchPanel.getAuditoriumDropDown().setEnabled(false);
            searchPanel.getSelectShowButton().setEnabled(false);
            searchPanel.resetSearch();
        });

        // KeyListener for the Reservation panel
        frame.addKeyListener(new KeyListener() {
            @Override public void keyTyped(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER && frame.getCurrentPanel() == reservationViewPanel && editReservation != null) {
                    Reservation r = new Reservation(editReservation.getId(), editReservation.getShow_id(),
                            reservationViewPanel.getReservationSeatPanel().newBooking(), editReservation.getAud_id(),
                            editReservation.getName(), editReservation.getContact_info()
                    );
                    Booking.editReservation(editReservation, r);
                    bookingViewPanel.updatePanels(currentShow, Booking.getAuditorium(currentShow.getAud_id()), Booking.getReservedSeats(currentShow.getId()).length);
                    bookingViewPanel.getInfoPanel().getBookButton().addActionListener(bookingButton);
                    if (Booking.getShow(editReservation.getShow_id()) == currentShow)  {
                        bookingViewPanel.getInfoPanel().updateSeatInfo(editReservation.getSeats().length, r.getSeats().length);
                    }
                    editReservation = null;
                    reservationViewPanel.getReservationSeatPanel().setClickable(false);
                    reservationViewPanel.getReservationList().updateEntries(Booking.getReservations());
                    reservationViewPanel.revalidate();
                    reservationViewPanel.repaint();
                }
            }
        });
        /*
          //This is currently not implemented correctly.
        reservationViewPanel.getReservationSearch().getSearchButton().addActionListener(e -> {
            String customerName = bookingViewPanel.getInfoPanel().getCustomerName();
            String customerPhone = bookingViewPanel.getInfoPanel().getCustomerPhone();
            customerName = customerName.replace(" ","");
            customerPhone = customerPhone.replace(" ","");
            reservationViewPanel.getReservationList().updateEntries(Booking.getReservations(
                    customerName,
                    customerPhone));
        });
         */
    }



    public static String[] getMovieTitles() {
        return Search.getMovies();
    }

    public void sendAnswer(int answer, Reservation r) {
        if (answer == JOptionPane.YES_OPTION) {
            editReservation = r;
            reservationViewPanel.updatePanels(Booking.getReservations(), r.getShow_id(), Booking.getAuditorium(r.getAud_id()));
            setupButtons();
            reservationViewPanel.getReservationSeatPanel().setClickable(true);
            reservationViewPanel.getReservationSeatPanel().setSelectedSeats(r.getSeats());
        }
        else if (answer == JOptionPane.NO_OPTION) {
            Booking.removeReservation(r);
            reservationViewPanel.updatePanels(Booking.getReservations(), r.getShow_id(), Booking.getAuditorium(r.getAud_id()));
            bookingViewPanel.updatePanels(currentShow, Booking.getAuditorium(currentShow.getAud_id()), Booking.getReservedSeats(currentShow.getId()).length);
            setupButtons();
        }
    }
}
