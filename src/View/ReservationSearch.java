package View;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

/**
 * ReservationSearch is a JPanel consisting of two JFormattedTextFields for typing user input, and a button to search for
 * reservations after name and contact info in the input.
 *
 * ReservationSearch uses gridlayout to display the different components.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class ReservationSearch extends JPanel{
    private final JButton searchButton;

    public ReservationSearch() {
        super(new GridLayout(1,4));
        setPreferredSize(new Dimension(1000, 20));
        setMaximumSize(new Dimension(1000, 30));
        try {
            MaskFormatter phoneRule = new MaskFormatter("########");
            JFormattedTextField contactPhone = new JFormattedTextField();
            JFormattedTextField contactName = new JFormattedTextField();
            phoneRule.install(contactPhone);
            add(new JLabel("          Contact Name:"));
            add(contactName);
            add(new JLabel("          Contact Number:"));
            add(contactPhone);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        searchButton = new JButton("Search");
        add(searchButton);
    }

    /**
     * This method returns the search button from the search panel.
     * @return A JButton in SearchPanel.
     */
    public JButton getSearchButton () {
        return searchButton;
    }
}