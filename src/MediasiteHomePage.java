import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

/**
 * Created by dmanzelmann on 5/20/2015.
 */
public class MediasiteHomePage {
    WebDriver driver;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "School of Pharmacy")
    WebElement schoolOfPharmacy;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "pharmD")
    WebElement pharmD;

    public void navigateToSchoolOfPharmacy() {
        schoolOfPharmacy.click();
    }

    public void navigateToPharmD() {
        pharmD.click();
    }


    public MediasiteHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }
}
