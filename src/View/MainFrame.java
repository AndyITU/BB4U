package View;

import Controller.Booking;
import Controller.ViewController;
import Model.Auditorium;
import Model.Show;

import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class BookingFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainFrame extends JFrame
{
    private BookingViewPanel bvPanel;
    private ReservationPanel rPanel;
    private SearchViewPanel searchPanel;
    private ButtonPanel bPanel;
    private Container pane;
    private JComponent currentPanel;

    public MainFrame(Show show, Auditorium auditorium, int reservedSeats) {
        super();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        bvPanel = new BookingViewPanel(show, auditorium, reservedSeats);
        bPanel = new ButtonPanel();
        rPanel = new ReservationPanel(Booking.getReservations(), show, auditorium);
        searchPanel = new SearchViewPanel(ViewController.getMovieTitles());

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1050,1000));

        // Loading screen
        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            return;
        }
        Graphics2D g = splash.createGraphics();
        if (g == null) {
            return;
        }
        for(int i=0; i<100; i++) {
            final String[] comps = {"foo", "bar", "baz"};
            g.setComposite(AlphaComposite.Clear);
            g.fillRect(120,140,200,40);
            g.setPaintMode();
            g.setColor(Color.BLACK);
            g.drawString("Loading "+comps[(i/5)%3]+"...", 120, 150);
            splash.update();
            try {
                Thread.sleep(90);
            }
            catch(InterruptedException e) {
            }
        }
        splash.close();

        setVisible(true);
        pane = getContentPane();

        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(bPanel);
        pane.add(bvPanel);

        this.pack();
    }
    public void updateMoviePanel(Show show, Auditorium auditorium, int reservedSeats) {
        bvPanel.updatePanels(show, auditorium, reservedSeats);
        repaint();
        pack();
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
    public ReservationPanel getReservationPanel() {
        return rPanel;
    }
    public JComponent getCurrentPanel() {
        return currentPanel;
    }

    public void changeToPanel (JComponent panel) {
        currentPanel = panel;
        pane.removeAll();
        pane.add(bPanel);
        pane.add(panel);
        repaint();
        pack();
    }

}