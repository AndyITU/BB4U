package View;

import javax.swing.*;
import java.awt.*;

/**
 * SearchViewPanel is an extension of JPanel, consisting of three JComboBoxes, and a JButton.
 *
 * The JComboBoxes contributes with a value(date, movie, auditorium) and
 * the button then registers the final show that was picked.
 *
 * It uses a gridlayout with 8 rows to display all of the components on top of eachother at the maximum available size.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */

public class SearchViewPanel extends JPanel {

    private final JComboBox<String> movieDropDown;
    private final JComboBox<String> dateDropDown;
    private final JComboBox<String> auditoriumDropDown;
    private final JButton selectShow = new JButton("Go to selected show");

    public SearchViewPanel(String[] movieTitles) {
        setLayout(new GridLayout(8,2));
        setPreferredSize(new Dimension(1000,50));
        setVisible(true);
        movieDropDown = new JComboBox<>(movieTitles);
        dateDropDown = new JComboBox<>();
        auditoriumDropDown = new JComboBox<>();
        JLabel selectMovie = new JLabel("Select a movie from the dropdown list");
        add(selectMovie);
        add(movieDropDown);
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
    public void updateMovies(String[] movieTitles) {
        movieDropDown.removeAllItems();
        for (String s : movieTitles) {
            movieDropDown.addItem(s);
            repaint();
        }
    }
    /**
     * This method receives a list of auditoriums, and inserts them into auditoriumDropDown.
     * @param auditoriumTitles The list of strings to put inside the dropdown box.
     */
    public void updateAud(String[] auditoriumTitles) {
        auditoriumDropDown.removeAllItems();
        for (String a : auditoriumTitles) auditoriumDropDown.addItem(a);
        repaint();
    }

    /**
     * This method receives a list of dates, and inserts them into dateDropDown.
     * @param dateTitles The list of strings to put inside the dropdown box.
     */
    public void updateDate(String[] dateTitles) {
        dateDropDown.removeAllItems();
        for (String d : dateTitles) dateDropDown.addItem(d);
        repaint();
    }

    /**
     * This method returns the dropdown list of movie titles.
     * @return
     */
    public JComboBox<String> getMovieDropDown() {
        return movieDropDown;
    }

    /**
     * @return This method returns the dropdown list of available dates.
     */
    public JComboBox<String> getDateDropDown() {
        return dateDropDown;
    }

    /**
     * @return This method returns the dropdown list of auditoriums.
     */
    public JComboBox<String> getAuditoriumDropDown() {
        return auditoriumDropDown;
    }

    /**
     * This method gets the button that selects a show from the panel.
     * @return selectShow
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

