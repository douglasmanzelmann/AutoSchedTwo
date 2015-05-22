package autoschedtwo.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class PortalScheduleHomePage extends Portal {
    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Event Types")
    WebElement eventTypes;

    public void selectAVEvents() {
        eventTypes.click();
        driver.findElement(By.partialLinkText("Events Requiring A/V Attention")).click();
    }


    public PortalScheduleHomePage(WebDriver driver) {
        super(driver);
    }
}
