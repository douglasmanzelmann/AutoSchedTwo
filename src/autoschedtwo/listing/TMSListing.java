package autoschedtwo.listing;

import autoschedtwo.tms.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class TMSListing extends Listing {
    private WebDriver driver;
    private String baltimoreLocation;
    private String sgLocation;

    public TMSListing(List<WebElement> listing) {
        super(listing);
        driver = new ChromeDriver();

        String[] locations = getLocation().split(" ");
        setBaltimoreLocation(locations[0] + " " + locations[1]);
        setSgLocation("USG " + locations[3]);
    }

    public String getSgLocation() {
        return sgLocation;
    }

    private void setSgLocation(String sgLocation) {
        this.sgLocation = sgLocation;
    }

    public String getBaltimoreLocation() {
        return baltimoreLocation;
    }

    private void setBaltimoreLocation(String baltimoreLocation) {
        this.baltimoreLocation = baltimoreLocation;
    }


    public void schedule(String username, String password) {
        String templateUserName = username.substring(1);

        TMSLoginPage loginPage = new TMSLoginPage(driver);

        TMSHomePage homePage = loginPage.login(username, password);

        // actually need to try a different codec.
        try {
            TMSConferenceTemplatesPage conferenceTemplatesPage = homePage.navigateToConferenceTemplates();

            TMSNewConferencePage newConferencePage =
                    conferenceTemplatesPage.selectTemplateforVTC(baltimoreLocation,sgLocation);
            TMSNewConferenceReviewPage newConferenceReviewPage =
                    newConferencePage.createNewTMSSlot(templateUserName, getDateInMDYFormat(getStartTime()),
                            getTime(getStartTime()), getDateInMDYFormat(getEndTime()), getTime(getEndTime()),
                            durationInMinutes(getStartTime(), getEndTime()));
        } catch (CodecInUseException e) { e.printStackTrace(); }

    }
}
