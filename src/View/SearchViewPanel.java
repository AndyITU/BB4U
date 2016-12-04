package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by arha on 12/2/2016.
 */

public class SearchViewPanel extends JPanel {

    private JComboBox movieDropDown;
    private JComboBox dateDropDown;
    private JComboBox auditoriumDropDown;
    private JButton selectShow;
    private String[] hardCodeValues = {"New Movie", "Old movie", "Kinda Old"};
    private String[] DATE_VALUES = {"27.October"};
    private Integer[] AUDITORIUM_ID_VALUES = {1,2,3,4};

    public SearchViewPanel() {
        setLayout(new GridLayout(8,2));
        setPreferredSize(new Dimension(1000,50));
        setVisible(true);
        JLabel selectMovie = new JLabel("Select a movie from the dropdown list");
        movieDropDown = new JComboBox(hardCodeValues);
        JLabel selectDate = new JLabel("Select a date from the dropdown list");
        dateDropDown = new JComboBox(hardCodeValues);
        JLabel selectAuditorium = new JLabel ("Select an auditorium from the dropdown list");
        auditoriumDropDown = new JComboBox(hardCodeValues);
        selectShow = new JButton("Go to selected show");

        add(selectMovie);
        add(movieDropDown);
        add(selectDate);
        add(dateDropDown);
        add(selectAuditorium);
        add(auditoriumDropDown);
        add(new JLabel(""));
        add(selectShow);
    }

    public JComboBox getMovieDropDown() {
        return movieDropDown;
    }
    public JComboBox getDateDropDown() {
        return dateDropDown;
    }
    public JComboBox getAuditoriumDropDown() {
        return auditoriumDropDown;
    }
    public JButton getSelectShowButton() {
        return selectShow;
    }
}

