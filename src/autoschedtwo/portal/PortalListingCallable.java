package autoschedtwo.portal;

import autoschedtwo.listing.Listing;
import autoschedtwo.listing.ListingFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.concurrent.Callable;

/**
 * Created by dmanzelmann on 5/29/2015.
 */
public class PortalListingCallable implements Callable<Listing>  {
    private ChromeOptions options;
    private WebDriver driver;
    private WebElement event;

    public PortalListingCallable(WebElement event) {
        driver = new HtmlUnitDriver();
        this.event = event;
    }

    public Listing call() {
        Listing newListing = ListingFactory.createListing(
                new PortalScheduleEventsEvent(driver, event.findElements(By.tagName("td"))).parse());
        return newListing;
    }
}
