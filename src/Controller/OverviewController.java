package Controller;

import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by arha on 12/3/2016.
 */
public class OverviewController {
    private OverviewFrame frame;
    private BookingViewPanel bookingViewPanel;
    private SearchViewPanel searchPanel;
    private ButtonPanel buttonPanel;

    public OverviewController(OverviewFrame frame) {
        bookingViewPanel = frame.getBookingPanel();
        searchPanel = frame.getSearchPanel();
        buttonPanel = frame.getButtonPanel();

        buttonPanel.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changeToSearch();
                frame.test();

            }
        });
    }


}
