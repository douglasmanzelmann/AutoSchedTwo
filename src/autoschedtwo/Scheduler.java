package autoschedtwo;

import autoschedtwo.listing.Listing;
import org.openqa.selenium.WebDriver;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public interface Scheduler {
    Listing schedule(String username, String password);
}
