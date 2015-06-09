package autoschedtwo.listing;

import autoschedtwo.Login;
import autoschedtwo.Scheduler;
import autoschedtwo.Screenshot;
import autoschedtwo.portal.PortalScheduleEventsEvent;
import autoschedtwo.tms.*;
import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class TMSListing extends Listing implements Screenshot {
    private ChromeOptions options;
    private WebDriver driver;
    private String baltimoreLocation;
    private String sgLocation;
    private File scrFile;
    private Login login;

    public TMSListing(PortalScheduleEventsEvent event) {
        super(event);

        if (getLocations().size() == 2) {
            baltimoreLocation = getLocations().get(0);
            sgLocation = getLocations().get(1).replace("SGIII", "USG");
        }
        else {
            // need to set a boolean, notAbleToSchedule
            baltimoreLocation = "UNKOWN";
            sgLocation = "UNKNOWN";
            setCanBeScheduled(false);
        }

        setActivity("VTC");
        setNeedsToBeScheduled(true);
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
        options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
        String templateUserName = WordUtils.capitalize(username.substring(1));

        TMSLoginPage loginPage = new TMSLoginPage(driver);

        TMSHomePage homePage = loginPage.login(username, password);

        // actually need to try a different codec.
        try {
            TMSConferenceTemplatesPage conferenceTemplatesPage = homePage.navigateToConferenceTemplates();

            TMSNewConferencePage newConferencePage =
                    conferenceTemplatesPage.selectTemplateforVTC(baltimoreLocation,sgLocation);
            TMSNewConferenceReviewPage newConferenceReviewPage =
                    newConferencePage.createNewTMSSlot(templateUserName, getDateInMDYFormat(getStartTime().plusDays(90)),
                            getTime(getStartTime()), getDateInMDYFormat(getEndTime().plusDays(90)), getTime(getEndTime()),
                            durationInMinutes(getStartTime(), getEndTime()));
        }
        //CodecInUseException
        catch (Exception e) {
            setStatus("failed");
            e.printStackTrace();
        }

        setStatus("finished");
        takeScreenshot(driver);
        driver.close();

        return this;
    }

    public void takeScreenshot(WebDriver driver) {
        scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    }

    public BufferedImage getStatusScreenshot() {
        BufferedImage image;
        try {
            image = ImageIO.read(scrFile);
            return image;
        }   catch (IOException e) { e.printStackTrace(); }


        return null;
    }

    /*public String toString() {
        return "Scheduled: " + scheduled + " " + super.toString();
    }*/
}
