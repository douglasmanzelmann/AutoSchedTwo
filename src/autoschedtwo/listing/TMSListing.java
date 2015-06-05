package autoschedtwo.listing;

import autoschedtwo.Login;
import autoschedtwo.Scheduler;
import autoschedtwo.portal.PortalScheduleEventsEvent;
import autoschedtwo.tms.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class TMSListing extends Listing {
    private WebDriver driver;
    private String baltimoreLocation;
    private String sgLocation;
    private Login login;
    private boolean scheduled;

    public TMSListing(PortalScheduleEventsEvent event) {
        super(event);

        if (getLocations().size() > 1) {
            baltimoreLocation = getLocations().get(0);
            sgLocation = getLocations().get(1).replace("SGIII", "USG");
        }
        else {
            // need to set a boolean, notAbleToSchedule
            baltimoreLocation = "UNKOWN";
            sgLocation = "UNKNOWN";
        }
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

    /*public Listing call() {
        schedule(login.getUsername(), login.getPassword());

        return this;
    }

    public void setLogin(Login login) {
        this.login = login;
    }*/

    public Listing schedule(String username, String password) {
        driver = new ChromeDriver();
        String templateUserName = username.substring(1);

        TMSLoginPage loginPage = new TMSLoginPage(driver);

        TMSHomePage homePage = loginPage.login(username, password);

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

        scheduled = true;

        return this;
    }

    /*public String toString() {
        return "Scheduled: " + scheduled + " " + super.toString();
    }*/
}
