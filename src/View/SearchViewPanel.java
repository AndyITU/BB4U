package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by arha on 12/2/2016.
 */

public class SearchViewPanel extends JPanel {

    public SearchViewPanel() {
        setLayout(new GridLayout(2,2));
        setPreferredSize(new Dimension(1000,50));
        setVisible(true);
        String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
        JComboBox petList = new JComboBox(petStrings);
        petList.addActionListener(petList);
        petList.setPreferredSize(new Dimension(100, 50));
        petList.setMaximumSize(new Dimension(100, 10));
        add(petList);
    }


}

