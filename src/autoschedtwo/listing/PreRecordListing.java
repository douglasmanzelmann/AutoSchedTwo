package autoschedtwo.listing;

import autoschedtwo.portal.PortalScheduleEventsEvent;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class PreRecordListing extends Listing  {
    public PreRecordListing(PortalScheduleEventsEvent event) {
        super(event);
    }

    public Listing schedule(String username, String password) {
        System.out.println("schedule pre-record");

        return this;
    }
}
