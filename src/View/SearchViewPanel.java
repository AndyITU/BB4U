package View;

import javax.swing.*;
import java.awt.*;

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
    public void updateAud(String[] auditoriumTitles) {
        auditoriumDropDown.removeAllItems();
        for (String a : auditoriumTitles) auditoriumDropDown.addItem(a);
        repaint();
    }
    public void updateDate(String[] dateTitles) {
        dateDropDown.removeAllItems();
        for (String d : dateTitles) dateDropDown.addItem(d);
        repaint();
    }
    public JComboBox<String> getMovieDropDown() {
        return movieDropDown;
    }
    public JComboBox<String> getDateDropDown() {
        return dateDropDown;
    }
    public JComboBox<String> getAuditoriumDropDown() {
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

