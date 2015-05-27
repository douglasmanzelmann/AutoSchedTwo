package autoschedtwo.outlook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by dmanzelmann on 5/27/2015.
 */
public class OutlookHomePage extends Outlook {
    @FindBy(how = How.ID, using = "lnkCal")
    WebElement lnkCal;

    public OutlookCalendarPage navigateToCalendar() {
        lnkCal.click();

        return PageFactory.initElements(driver, OutlookCalendarPage.class);
    }

    public OutlookHomePage(WebDriver driver) {
        super(driver);
    }
}
