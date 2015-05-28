package autoschedtwo;

import autoschedtwo.listing.Listing;
import autoschedtwo.portal.PortalScheduleEventsEvent;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dmanzelmann on 5/28/2015.
 */
public class AutoSchedWorker extends SwingWorker<DefaultListModel<Listing>, String> {
    private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private DefaultListModel<Listing> listingDefaultListModel;
    private String username;
    private String password;
    private int year;
    private int month;
    private int day;

    public AutoSchedWorker(DefaultListModel<Listing> listingDefaultListModel, String username,
                           String password, int year, int month, int day) {
        this.listingDefaultListModel = listingDefaultListModel;
        this.username = username;
        this.password = password;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    protected DefaultListModel<Listing> doInBackground() throws Exception {
        AutoSched autoSched = new AutoSched();
        List<PortalScheduleEventsEvent> portalScheduleEventsEventList =
                autoSched.readPortalSchedule(username, password, year, month, day);
    }
}
