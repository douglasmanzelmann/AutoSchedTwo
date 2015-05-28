package autoschedtwo.listing;

import autoschedtwo.portal.PortalScheduleEventsEvent;
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

    public TMSListing(PortalScheduleEventsEvent event) {
        super(event);

        setBaltimoreLocation(getLocations().get(0));
        setSgLocation(getLocations().get(1).replace("SGIII", "USG"));
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


    public void schedule(String username, String password, String tmsUsername, String tmsPassword) {
        driver = new ChromeDriver();
        String templateUserName = username.substring(1);

        TMSLoginPage loginPage = new TMSLoginPage(driver);

        TMSHomePage homePage = loginPage.login(tmsUsername, tmsPassword);

        // actually need to try a different codec.
        try {
            TMSConferenceTemplatesPage conferenceTemplatesPage = homePage.navigateToConferenceTemplates();

            TMSNewConferencePage newConferencePage =
                    conferenceTemplatesPage.selectTemplateforVTC(baltimoreLocation,sgLocation);
            TMSNewConferenceReviewPage newConferenceReviewPage =
                    newConferencePage.createNewTMSSlot(templateUserName, getDateInMDYFormat(getStartTime().plusDays(20)),
                            getTime(getStartTime()), getDateInMDYFormat(getEndTime().plusDays(20)), getTime(getEndTime()),
                            durationInMinutes(getStartTime(), getEndTime()));
        } catch (CodecInUseException e) { e.printStackTrace(); }

    }
}
