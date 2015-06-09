package autoschedtwo.listing;

import autoschedtwo.portal.PortalScheduleEventsEvent;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class PreRecordListing extends Listing  {
    public PreRecordListing(PortalScheduleEventsEvent event) {
        super(event);
        setActivity("Pre-record");
        setNeedsToBeScheduled(false);
        setStatus("NA");
    }

    public Listing schedule(String username, String password) {
        System.out.println("schedule pre-record");

        return this;
    }

    public void takeScreenshot(WebDriver driver) {

    }

    public BufferedImage getStatusScreenshot() {
        BufferedImage stubImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        return stubImage;
    }
}
