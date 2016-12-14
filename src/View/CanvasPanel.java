package View;

import javax.swing.*;
import java.awt.*;
/**
 * CanvasPanel is an extension of JPanel. It's only function is to draw the graphical outlines of a screen.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
class CanvasPanel extends JPanel {
    /**
     * The constructor of CanvasPanel sets up the layout of the CanvasPanel with a BorderLayout.
     */
    CanvasPanel() {
        super(new BorderLayout());
        setVisible(true);
        setMaximumSize(new Dimension(1000, 200));
        setPreferredSize(new Dimension(1000, 100 ));
    }

    /**
     * This method draws two squares, a black one and a grey one. On top of the squares are written "Screen".
     * @param g the graphical component which all drawing methods are called from.
     */
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
