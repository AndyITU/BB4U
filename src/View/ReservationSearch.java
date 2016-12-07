package View;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

/**
 * Created by Mikkel on 07-12-2016.
 */
public class ReservationSearch extends JPanel{
    private JFormattedTextField contactPhone;
    private JFormattedTextField contactName;

    public ReservationSearch() {
        try {
            MaskFormatter phoneRule = new MaskFormatter("########");
            MaskFormatter nameRule = new MaskFormatter("?????????????????????????????????");
            contactPhone = new JFormattedTextField();
            contactName = new JFormattedTextField();
            nameRule.install(contactName);
            phoneRule.install(contactPhone);
            add(new JLabel("Contact Number:"));
            add(contactPhone);
            add(new JLabel("Contact Name:"));
            add(contactName);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
