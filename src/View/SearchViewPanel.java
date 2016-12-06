package View;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

/**
 * Created by arha on 12/2/2016.
 */

public class SearchViewPanel extends JPanel {

    private JComboBox movieDropDown;
    private JComboBox dateDropDown;
    private JComboBox auditoriumDropDown;
    private JButton selectShow = new JButton("Go to selected show");;
    JLabel selectMovie = new JLabel("Select a movie from the dropdown list");
    JLabel selectDate = new JLabel("Select a date from the dropdown list");
    private JLabel selectAuditorium = new JLabel ("Select an auditorium from the dropdown list");

    public SearchViewPanel(String[] movieTitles) {
        setLayout(new GridLayout(8,2));
        setPreferredSize(new Dimension(1000,50));
        setVisible(true);
        movieDropDown = new JComboBox<>(movieTitles);
        dateDropDown = new JComboBox<>();
        auditoriumDropDown = new JComboBox<>();
        add(selectMovie);
        add(movieDropDown);
        add(selectDate);
        add(dateDropDown);
        add(selectAuditorium);
        add(auditoriumDropDown);
        add(new JLabel(""));
        add(selectShow);
        dateDropDown.setEnabled(false);
        auditoriumDropDown.setEnabled(false);
        selectShow.setEnabled(false);
    }
    public void updateAud(String[] auditoriumTitles) {
        auditoriumDropDown.removeAll();
        for (String a : auditoriumTitles) auditoriumDropDown.addItem(a);
        repaint();
    }
    public void updateDate(String[] dateTitles) {
        dateDropDown.removeAll();
        for (String d : dateTitles) dateDropDown.addItem(d);
        repaint();
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
    public void resetSearch() {
        dateDropDown.setSelectedItem("");
        auditoriumDropDown.setSelectedItem("");
    }
}

