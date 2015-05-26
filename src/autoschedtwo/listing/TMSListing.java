package autoschedtwo.listing;

import autoschedtwo.tms.TMSConferenceTemplatesPage;
import autoschedtwo.tms.TMSHomePage;
import autoschedtwo.tms.TMSLoginPage;
import autoschedtwo.tms.TMSNewConferencePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class TMSListing extends Listing {
    WebDriver driver;

    public TMSListing(List<WebElement> listing) {
        super(listing);
        driver = new ChromeDriver();
    }

    public void schedule(String username, String password) {


    }
}
