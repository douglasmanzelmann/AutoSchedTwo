package autoschedtwo.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class PortalScheduleEventsPage extends Portal {
    WebDriverWait wait;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Event Types")
    WebElement eventTypes;

    public void selectAVEvents() {
        eventTypes.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("popupMenu")));
        WebElement popupMenu = driver.findElement(By.id("popupMenu"));
        popupMenu.findElement(By.partialLinkText("Events Requiring A/V Attention")).click();
    }


    public PortalScheduleEventsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 30);
    }
}
