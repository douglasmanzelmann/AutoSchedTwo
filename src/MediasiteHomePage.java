import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by dmanzelmann on 5/20/2015.
 */
public class MediasiteHomePage {
    WebDriver driver;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "School of Pharmacy")
    WebElement schoolOfPharmacy;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "PharmD")
    WebElement pharmD;

    public void navigateToSchoolOfPharmacy() {
        schoolOfPharmacy.click();
    }

    public void navigateToPharmD() {
        pharmD.click();
    }

    public MediasiteFolderPage navigateToFolder(String folder) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(folder)));
        driver.findElement(By.partialLinkText(folder)).click();

        return new MediasiteFolderPage(driver);
    }

    public MediasiteHomePage(WebDriver driver) {
        this.driver = driver;
    }

}
