package autoschedtwo.tms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class TMSConferenceTemplatesPage extends TMS {

    public TMSNewConferencePage selectTemplateforVTC(String baltimoreRoom, String sgRoom
    ) {
        WebElement template;

        try {
            template = driver.findElement(By.partialLinkText(TMSUtils.chooseBaltimoreCodec(baltimoreRoom) + " - " + sgRoom));
        } catch (org.openqa.selenium.NoSuchElementException e) {
            template = driver.findElement(By.partialLinkText(TMSUtils.chooseBaltimoreCodec(baltimoreRoom) + "- " + sgRoom));
        }

        new Actions(driver).moveToElement(template).moveByOffset(50,0).click().build().perform();
        driver.findElement(By.partialLinkText("Use As Conference")).click();

        return PageFactory.initElements(driver, TMSNewConferencePage.class);
    }

    public TMSConferenceTemplatesPage(WebDriver driver) {
        super(driver);
    }
}
