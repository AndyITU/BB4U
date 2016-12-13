package View;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * SearchViewPanel is an extension of JPanel, consisting of three JComboBoxes, and a JButton.
 *
 * The JComboBoxes contributes with a value(date, movie, auditorium) and
 * the button then registers the final show that was picked.
 *
 * It uses a gridlayout with 8 rows to display all of the components on top of each other at the maximum available size.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */

public class SearchViewPanel extends JPanel {

    private final JComboBox<String> movieDropDown;
    private final JComboBox<String> dateDropDown;
    private final JComboBox<String> auditoriumDropDown;
    private final JButton selectShow = new JButton("Go to selected show");

    /**
     * The constructor sets a gridlayout.
     * @param movieTitles
     */

    public SearchViewPanel(String[] movieTitles) {
        setLayout(new GridLayout(8,2));
        setPreferredSize(new Dimension(1000,50));
        setVisible(true);
        movieDropDown = new JComboBox<>();
        dateDropDown = new JComboBox<>();
        auditoriumDropDown = new JComboBox<>();
        JLabel selectMovie = new JLabel("Select a movie from the dropdown list");
        add(selectMovie);
        add(movieDropDown);
        updateMovies(movieTitles);
        JLabel selectDate = new JLabel("Select a date from the dropdown list");
        add(selectDate);
        add(dateDropDown);
        JLabel selectAuditorium = new JLabel("Select an auditorium from the dropdown list");
        add(selectAuditorium);
        add(auditoriumDropDown);
        add(new JLabel(""));
        add(selectShow);
        dateDropDown.setEnabled(false);
        auditoriumDropDown.setEnabled(false);
        selectShow.setEnabled(false);
    }
    /**
     * This method receives a list of movie titles, and inserts them into movieDropDown. Duplicate strings are removed, and the strings
     * gets sorted by alphabetic order.
     *
     * @param movieTitles The list of strings to put inside the dropdown box.
     **/
    public void updateMovies(String[] movieTitles) {
        movieDropDown.removeAllItems();
        Set<String> movies = new HashSet<>();
        for (String m : movieTitles) {
            movies.add(m);
        }
        ArrayList<String> arrayListmovies = new ArrayList<>(movies);
        Collections.sort(arrayListmovies);
        for (String m : arrayListmovies) {
            movieDropDown.addItem(m);
        }
    }
    /**
     * This method receives a list of auditoriums, and inserts them into auditoriumDropDown. Duplicate strings are removed, and the strings
     * gets sorted by alphabetic order
     * @param auditoriumTitles The list of strings to put inside the dropdown box.
     */
    public void updateAud(String[] auditoriumTitles) {
        auditoriumDropDown.removeAllItems();
        Set<String> auditoriums = new HashSet<>();
        for (String d : auditoriumTitles) {
            auditoriums.add(d);

        }
        ArrayList<String> auditoriumArrayList = new ArrayList<>(auditoriums);
        Collections.sort(auditoriumArrayList);
        for (String k : auditoriumArrayList) {
            auditoriumDropDown.addItem(k);
        }
        repaint();
    }

    /**
     * This method receives a list of dates, and inserts them into dateDropDown. Duplicate strings are removed, and they
     * are sorted by alphabetic order.
     * @param dateTitles The list of strings to put inside the dropdown box.
     */
    public void updateDate(String[] dateTitles) {
        dateDropDown.removeAllItems();
        Set<String> movieDates = new HashSet<>();
        for (String d : dateTitles) {
            movieDates.add(d);
        }
        ArrayList<String> dateArrayList = new ArrayList<>(movieDates);
        Collections.sort(dateArrayList);
        for (String k : movieDates) {
            dateDropDown.addItem(k);}
        repaint();
    }

    /**
     * This method returns the JComboBox that consists of movie titles.
     * @return the element movieDropDown in SearchViewPanel.
     */
    public JComboBox<String> getMovieDropDown() {
        return movieDropDown;
    }

    /**
     * This method returns the JComboBox consisting of available dates.
     * @return dateDropDown in SearchViewPanel.
     */
    public JComboBox<String> getDateDropDown() {
        return dateDropDown;
    }

    /**
     * This method returns the JComboBox consisting of available auditoriums.
     * @return auditoriumDropDown in SearchViewPanel.
     */
    public JComboBox<String> getAuditoriumDropDown() {
        return auditoriumDropDown;
    }

    /**
     * This method returns the JButton that selects a show from the panel.
     * @return selectShow JButton in SearchViewPanel.
     */
    public JButton getSelectShowButton() {
        return selectShow;
    }

    /**
     * This method resets the selected words in the dropdown menus, such that it is not necessary for the user to do that.
     * The dropdown menus have a built-in empty space.
     */
    public void resetSearch() {
        dateDropDown.setSelectedItem("");
        auditoriumDropDown.setSelectedItem("");
    }
}

