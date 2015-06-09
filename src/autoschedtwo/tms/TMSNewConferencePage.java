package autoschedtwo.tms;

import org.joda.time.DateTime;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class TMSNewConferencePage extends TMS {
    JavascriptExecutor js;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ctl00_uxContent_ctl01_conferenceTime_dpStartDate_textBox\"]")
    WebElement startDate;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ctl00_uxContent_ctl01_conferenceTime_tbStartTime\"]")
    WebElement startTime;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ctl00_uxContent_ctl01_conferenceTime_dpEndDate_textBox\"]")
    WebElement endDate;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ctl00_uxContent_ctl01_conferenceTime_tbEndTime\"]")
    WebElement endTime;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ctl00_uxContent_ctl01_conferenceTime_tbDuration\"]")
    WebElement duration;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ctl00_uxContent_ctl01_uxUserSelector_uxSelectUsersButton\"]")
    WebElement selectUser;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ctl00_uxContent_ctl01_saveButton\"]")
    WebElement save;

    public void setStartDate(String date) throws Exception {
        try {
            js.executeScript("arguments[0].value = '" + date + "';", startDate);
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            startDate = driver.findElement(By.xpath("//*[@id=\"ctl00_uxContent_ctl01_conferenceTime_dpStartDate_textBox\"]"));
            js.executeScript("arguments[0].value = '" + date + "';", startDate);
        }
    }

    public void setStartTime(String time) {
        js.executeScript("arguments[0].value = '" + time + "';", startTime);
    }

    public void setEndDate(String date) {
        js.executeScript("arguments[0].value = '" + date + "';", endDate);
    }

    public void setEndTime(String time) {
        js.executeScript("arguments[0].value = '" + time + "';", endTime);
    }

    public void setDuration(String d) {
        js.executeScript("arguments[0].value = '" + d + "';", duration);
    }


    // make it's own page object?
    public void setUser(String user) {
        selectUser.click();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        String mainWindow = iterator.next();
        String userWindow = iterator.next();
        driver.switchTo().window(userWindow);
        driver.findElement(By.partialLinkText(user)).click();
        driver.switchTo().window(mainWindow);
    }

    public void save() {
        save.click();
    }

    //CodecInUseException
    public TMSNewConferenceReviewPage createNewTMSSlot(String user, String sd, String st, String ed, String et, String d)
        throws Exception {

        setUser(user);
        setStartDate(sd);
        setStartTime(st);
        setEndDate(ed);
        setEndTime(et);
        setDuration(d);
        save();

        List<WebElement> errors = driver.findElements(By.id("//*[@id=\"ctl00_uxContent_uxFeedback_uxErrorLabel\"]"));
        if (errors.size() == 1) {
            if (errors.get(0).getText().contains("The system is already scheduled to be used at this time"))
                throw new CodecInUseException();
        }


        return PageFactory.initElements(driver, TMSNewConferenceReviewPage.class);
    }

    public TMSNewConferencePage(WebDriver driver) {
        super(driver);
        js = (JavascriptExecutor) driver;
    }
}
