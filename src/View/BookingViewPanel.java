package View;

import javax.swing.*;

/**
 * Created by arha on 12/2/2016.
 */
public class BookingViewPanel extends JPanel {

    private SeatPanel sPanel;
    private InformationPanel iPanel;
    private CanvasPanel cPanel;

    public BookingViewPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        sPanel = new SeatPanel(4,10); iPanel = new InformationPanel(sPanel.getSeatPanel()); cPanel = new CanvasPanel();
        add(cPanel); this.add(sPanel); this.add(iPanel);
        setVisible(true);
    }

    public InformationPanel getInfoPanel() {
        return iPanel;
    }
}
