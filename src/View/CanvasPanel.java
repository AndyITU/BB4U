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
        this.setMinimumSize(new Dimension(getWidth(), 250));
        this.setMaximumSize(new Dimension(1000, 100));
    }

    public void paint (Graphics g) {
        g.drawRect(
                getWidth()/4,
                0,
                getWidth()/4*3,
                getHeight());
    }

}
