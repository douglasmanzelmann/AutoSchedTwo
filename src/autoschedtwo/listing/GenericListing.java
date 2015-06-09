package autoschedtwo.listing;

import autoschedtwo.portal.PortalScheduleEventsEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class GenericListing extends Listing {
    public GenericListing(PortalScheduleEventsEvent event) {
        super(event);
        setActivity("");
        setNeedsToBeScheduled(false);
        setStatus("NA");
    }

    public Listing schedule(String username, String password) {
        System.out.println("Schedule generic listing");

        return this;
    }

    public void takeScreenshot(WebDriver driver) {

    }

    public BufferedImage getStatusScreenshot() {
        BufferedImage stubImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        return stubImage;
    }
}
