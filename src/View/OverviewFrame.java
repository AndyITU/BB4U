package View;

import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class BookingFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OverviewFrame extends JFrame
{
    private static BookingViewPanel bvPanel;
    private static SearchViewPanel searchPanel;
    private static ButtonPanel bPanel;
    private static Container pane;

    public static void main (String[] args ) {
    }
    public OverviewFrame () {
        super();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        bvPanel = new BookingViewPanel();
        bPanel = new ButtonPanel();
        searchPanel = new SearchViewPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000,1000));
        setVisible(true);
        pane = getContentPane();

        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(bPanel);
        pane.add(bvPanel);

        this.pack();
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
    public void changeToPanel (JComponent panel) {
        pane.removeAll();
        pane.add(bPanel);
        pane.add(panel);
        repaint();
        pack();
    }

}