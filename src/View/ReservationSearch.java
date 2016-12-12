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
    private JButton searchButton;

    public ReservationSearch() {
        super(new GridLayout(1,4));
        setPreferredSize(new Dimension(1000, 20));
        setMaximumSize(new Dimension(1000, 30));
        try {
            MaskFormatter phoneRule = new MaskFormatter("########");
            MaskFormatter nameRule = new MaskFormatter("***************************************************");
            nameRule.setValidCharacters("ABCDEFGHIJKLMNOPQRSTUVXYZWÆØÅabcdefghijklmnopqrstuvxyzwæøå ");
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
        searchButton = new JButton("Search");
        add(searchButton);
    }
    public JButton getSearchButton () {
        return searchButton;
    }
    public String getCustomerName() {
        return contactName.getText();
    }
    public String getCustomerPhone()    {
        return contactPhone.getText();
    }


}