package autoschedtwo.tms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class TMSHomePage extends TMS {
    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Booking")
    WebElement booking;

    public TMSConferenceTemplatesPage navigateToConferenceTemplates() throws Exception {
        new Actions(driver).moveToElement(booking).build().perform();
        booking.findElement(By.xpath("//span[text()='Conference Templates']")).click();

        return PageFactory.initElements(driver, TMSConferenceTemplatesPage.class);
    }

    public TMSHomePage(WebDriver driver) {
        super(driver);
        driver.switchTo().activeElement().click();
    }
}
