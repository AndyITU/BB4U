package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by arha on 11/29/2016.
 */
// Work in progress
public class CanvasPanel extends JPanel {

    public CanvasPanel() {
        super();
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.add(new JLabel("Big Screen"), BorderLayout.CENTER);
        this.setMaximumSize(new Dimension(1000, 200));
        this.setPreferredSize(new Dimension(1000, 100 ));
    }

    public void paint (Graphics g) {
        g.drawRect(
                getWidth()/4,
                5,
                getWidth()/2,
                getHeight()-6);
        g.drawString("Screen", getWidth()/2-16, getHeight()/2+5);

    }

}
