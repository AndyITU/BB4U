package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mikkel on 07-12-2016.
 */
public class ReservationView extends JPanel{

    public ReservationView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1000, 1000));
        for (int i = 0; i < 10; i++) {
            add(new ReservationEntry());
        }
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);
    }
}
