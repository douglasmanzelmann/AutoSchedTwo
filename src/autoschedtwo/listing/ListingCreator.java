package autoschedtwo.listing;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class ListingCreator {
    private static int ACTIVITY_TYPE_INDEX = 3;

    public static Listing createListing(List<WebElement> listing) {
        if (listing.get(ACTIVITY_TYPE_INDEX).getText().contains("Recorded in Mediasite"))
            return new MediasiteListing(listing);
        else if (listing.get(ACTIVITY_TYPE_INDEX).getText().contains("Videoconference"))
            return new TMSListing(listing);
        else if (listing.get(ACTIVITY_TYPE_INDEX).getText().contains("Pre-record Session "))
            return new PreRecordListing(listing);
        else
            return new GenericListing(listing);
    }
}
