package autoschedtwo.mediasite;

import autoschedtwo.mediasite.Mediasite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by dmanzelmann on 5/21/2015.
 */
public class MediasiteTemplatePage extends Mediasite {
    WebDriver driver;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "SOP Standard Template (2014)")
    WebElement sopStandardTemplate;

    public MediasiteNewPresentationPage selectDefaultTemplate() {
        sopStandardTemplate.click();

        return new MediasiteNewPresentationPage(driver);
    }

    public MediasiteTemplatePage(WebDriver driver) {
        super(driver);
    }

}
