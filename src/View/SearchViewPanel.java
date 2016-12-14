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
     * The constructor sets up the entire search interface. Using a list of strings that are applied to the first JComboBox,
     * the user is able of selecting a single movie on a specific date and/or in a specific auditorium.
     * @param movieTitles the list of movies to add to the SearchViewPanel.
     */

    SearchViewPanel(String[] movieTitles) {
        setLayout(new GridLayout(8,2));
        setPreferredSize(new Dimension(1000,50));
        setVisible(true);
        movieDropDown = new JComboBox<>();
        dateDropDown = new JComboBox<>();
        auditoriumDropDown = new JComboBox<>();
        JLabel selectMovie = new JLabel("Select a movie from the dropdown list");
        add(selectMovie);
        add(movieDropDown);
        updateInfo(movieTitles,movieDropDown);
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
     * This method receives a list of info for a specified JComboBox, and inserts them into it. Duplicate strings are removed, and they
     * are sorted by alphabetic order..
     * @param dropDownTitles The list of strings to put inside the dropdown box.
     */
    public void updateInfo(String[] dropDownTitles, JComboBox j) {
        j.removeAllItems();
        Set<String> duplicateRemove = new HashSet<>();
        Collections.addAll(duplicateRemove, dropDownTitles);
        ArrayList<String> dateArrayList = new ArrayList<>(duplicateRemove);
        Collections.sort(dateArrayList);
        for (String k : dateArrayList) {
            j.addItem(k);
        }
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

