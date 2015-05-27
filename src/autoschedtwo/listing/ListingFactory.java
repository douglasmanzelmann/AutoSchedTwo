package autoschedtwo.listing;

import autoschedtwo.portal.PortalScheduleEventsEvent;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class ListingFactory {
    private static int ACTIVITY_TYPE_INDEX = 3;

    public static Listing createListing(PortalScheduleEventsEvent event) {
        if (event.getActivity().equals("Mediasite"))
            return new MediasiteListing(event);
        else if (event.getActivity().equals("Videoconference"))
            return new TMSListing(event);
        else if (event.getActivity().equals("Pre-record Session"))
            return new PreRecordListing(event);
        else
            return new GenericListing(event);


        /*if (listing.get(ACTIVITY_TYPE_INDEX).getText().contains("Recorded in Mediasite"))
            return new MediasiteListing(listing);
        else if (listing.get(ACTIVITY_TYPE_INDEX).getText().contains("Videoconference"))
            return new TMSListing(listing);
        else if (listing.get(ACTIVITY_TYPE_INDEX).getText().contains("Pre-record Session"))
            return new PreRecordListing(listing);
        else
            return new GenericListing(listing);*/
    }
}
