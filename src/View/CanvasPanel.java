package View;

import javax.swing.*;
import java.awt.*;

class CanvasPanel extends JPanel {

    CanvasPanel() {
        setLayout(new BorderLayout());
        setVisible(true);
        add(new JLabel("Big Screen"), BorderLayout.CENTER);
        setMaximumSize(new Dimension(1000, 200));
        setPreferredSize(new Dimension(1000, 100 ));
    }

    public void paint (Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(
                getWidth()/4,
                5,
                getWidth()/2,
                getHeight()-6);
        g.setColor(Color.BLACK);
        g.drawRect(
                getWidth()/4,
                5,
                getWidth()/2,
                getHeight()-6);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("Screen", getWidth()/2-38, getHeight()/2+6);

    }

}
