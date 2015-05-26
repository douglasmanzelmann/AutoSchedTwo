package autoschedtwo.listing;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class GenericListing extends Listing {
    public GenericListing(List<WebElement> listing) {
        super(listing);
    }

    public void schedule(String username, String password) {

    }
}
