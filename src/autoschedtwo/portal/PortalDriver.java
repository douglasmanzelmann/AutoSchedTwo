package autoschedtwo.portal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class PortalDriver extends Portal {
    public ArrayList<ArrayList<WebElement>> getScheduleElements(String username, String password, int year, int month, int day) {
        driver.get("https://rxsecure.umaryland.edu/apps/schedules/view/?type=search&searchtype=resource&id=100&start=" +
                year + "-" + month + "-" + day + "&scope=week");
        PortalLoginPage loginPage = PageFactory.initElements(driver, PortalLoginPage.class);
        PortalScheduleEventsWeekPage scheduleEventsWeekPage = loginPage.login(username, password);
        return scheduleEventsWeekPage.getScheduleElements();
    }

    public PortalDriver(WebDriver driver) {
        super(driver);
    }
}
