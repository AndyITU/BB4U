package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by arha on 11/29/2016.
 */
// Work in progress // Never mind, is now fully functional :D
public class CanvasPanel extends JPanel {

    public CanvasPanel() {
        setLayout(new BorderLayout());
        setVisible(true);
        add(new JLabel("Big Screen"), BorderLayout.CENTER);
        setMaximumSize(new Dimension(1000, 200));
        setPreferredSize(new Dimension(1000, 100 ));
    }

    public void paint (Graphics g) {
        g.drawRect(
                getWidth()/4,
                5,
                getWidth()/2,
                getHeight()-6);
        g.drawString("Screen", getWidth()/2-20, getHeight()/2+5);

    }

}
