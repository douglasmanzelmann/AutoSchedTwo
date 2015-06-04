package autoschedtwo.listing;

import autoschedtwo.portal.PortalScheduleEventsEvent;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class ListingFactory implements Callable<Listing> {
    private PortalScheduleEventsEvent event;

    public ListingFactory(PortalScheduleEventsEvent event) {
        this.event = event;
    }

    public Listing call() {
        if (event.getActivity().equals("Mediasite"))
            return new MediasiteListing(event);
        else if (event.getActivity().equals("Videoconference"))
            return new TMSListing(event);
        /*else if (event.getActivity().equals("Pre-record Session"))
            return new PreRecordListing(event);
        else
            return new GenericListing(event);*/
        return null;
    }

    public static Listing createListing(PortalScheduleEventsEvent event) {
        if (event.getActivity().equals("Mediasite"))
            return new MediasiteListing(event);
        else if (event.getActivity().equals("Videoconference"))
            return new TMSListing(event);
        /*else if (event.getActivity().equals("Pre-record Session"))
            return new PreRecordListing(event);
        else
            return new GenericListing(event);*/
        return null;
    }
}
