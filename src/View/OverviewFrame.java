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
    private static BookingViewPanel bvPanel;
    private static SearchViewPanel searchPanel;
    private static ButtonPanel bPanel;
    private static JFrame frame;

    public static void main (String[] args ) {
    }
    public OverviewFrame () {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        bvPanel = new BookingViewPanel();
        bPanel = new ButtonPanel();
        searchPanel = new SearchViewPanel();
        frame = new JFrame("Booking Overview");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000,1000));
        frame.setVisible(true);

        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(bPanel);
        frame.getContentPane().add(bvPanel);
        frame.pack();
    }
    public BookingViewPanel getBookingPanel() {
        return bvPanel;
    }
    public SearchViewPanel getSearchPanel() {
        return searchPanel;
    }

    public ButtonPanel getButtonPanel() {
        return bPanel;
    }
    public void changeToSearch() {
        frame.remove(bvPanel);
        frame.add(searchPanel);
    }
    public void test() {
        System.out.println("hej");
    }

}