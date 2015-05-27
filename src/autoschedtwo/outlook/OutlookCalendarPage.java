package autoschedtwo.outlook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by dmanzelmann on 5/27/2015.
 */
public class OutlookCalendarPage extends Outlook {
    @FindBy(how = How.ID, using = "day")
    WebElement dayView;

    private void setDayView() {
        dayView.click();
    }

    private void setDayToSchedule(int date) {
        driver.findElement(By.xpath("//span[text()='" + date + "']")).click();
    }

    private void scheduleNewAppointment() {
        driver.findElement(By.xpath("//*[@id=\"newapptc\"]/span")).click();
    }

    public OutlookNewAppointmentPage scheduleNewAppointmentOnDay(int day) {
        driver.switchTo().frame(0);
        setDayView();
        driver.switchTo().defaultContent();
        setDayToSchedule(day);
        driver.switchTo().frame(0);
        scheduleNewAppointment();

        return PageFactory.initElements(driver, OutlookNewAppointmentPage.class);
    }

    public OutlookCalendarPage(WebDriver driver) {
        super(driver);
    }
}
