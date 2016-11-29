package View;

import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class BookingFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OverviewFrame
{
    private static SeatPanel sPanel;
    private static ButtonPanel bPanel;
    private static InformationPanel iPanel;
    // private static CanvasPanel cPanel;
    private static JFrame frame;

    public static void main (String[] args ) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        System.out.println(args);
        sPanel = new SeatPanel(10,10); bPanel = new ButtonPanel(); iPanel = new InformationPanel(sPanel.getSeats()); // cPanel = new CanvasPanel();
        frame = new JFrame("Booking Overview");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000,1000));
        frame.setVisible(true);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(bPanel);
        //frame.getContentPane().add(cPanel);
        frame.getContentPane().add(sPanel);
        frame.getContentPane().add(iPanel);
        frame.pack();
    }
    public OverviewFrame () {

    }

    private void setSearchPage() {

    }
    
}