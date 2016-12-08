package View;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

/**
 * Created by Mikkel on 07-12-2016.
 */
public class ReservationSearch extends JPanel{
    private JFormattedTextField contactPhone;
    private JFormattedTextField contactName;
    private JButton searchReservations;

    public ReservationSearch() {
        super(new GridLayout(1,4));
        setPreferredSize(new Dimension(1000, 50));
        try {
            MaskFormatter phoneRule = new MaskFormatter("########");
            MaskFormatter nameRule = new MaskFormatter("?????????????????????????????????");
            contactPhone = new JFormattedTextField();
            contactPhone.setMaximumSize(new Dimension(getWidth()/3, 5));
            contactName = new JFormattedTextField();
            nameRule.install(contactName);
            phoneRule.install(contactPhone);
            add(new JLabel("          Contact Name:"));
            add(contactName);
            add(new JLabel("          Contact Number:"));
            add(contactPhone);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Needed some help changing the color of this one: http://stackoverflow.com/questions/1065691/how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
        searchReservations = new JButton("Search!");
        searchReservations.setBackground(Color.BLACK);
        searchReservations.setBorderPainted(false);
        searchReservations.setOpaque(true);
        add(searchReservations);

    }
}