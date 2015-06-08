package autoschedtwo.mediasite;
import autoschedtwo.mediasite.Mediasite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by dmanzelmann on 5/20/2015.
 */
public class MediasiteHomePage extends Mediasite {
    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "School of Pharmacy")
    WebElement schoolOfPharmacy;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "PharmD")
    WebElement pharmD;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Training")
    WebElement training;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Testing")
    WebElement testing;

    public void navigateToSchoolOfPharmacy() {
        schoolOfPharmacy.click();
    }

    public void navigateToPharmD() {
        pharmD.click();
    }

    public void navigateToTraining() {
        training.click();
    }

    public void navigateToTesting() {
        testing.click();
    }

    public MediasiteFolderPage navigateToNowhere() {
        return new MediasiteFolderPage(driver);
    }

    public MediasiteFolderPage navigateToFolder(String folder) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(folder)));
        driver.findElement(By.partialLinkText(folder)).click();

        return new MediasiteFolderPage(driver);
    }

    public MediasiteHomePage(WebDriver driver) {
        super(driver);
    }

}
