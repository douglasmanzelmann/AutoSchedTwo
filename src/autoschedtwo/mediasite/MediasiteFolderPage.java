package autoschedtwo.mediasite;

import autoschedtwo.mediasite.Mediasite;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

/**
 * Created by dmanzelmann on 5/21/2015.
 */
public class MediasiteFolderPage extends Mediasite{
    WebDriver driver;

    //@FindBy(how = How.ID, using = "AddNewEntity")
    //WebElement presentationAddSelect;

    public MediasiteTemplatePage addNewPresentation() {
        // shouldn't have to do this
        // using new AjaxElementLocatorFactory(driver, 30)
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddNewEntity_Element")));

        // This... is... ugly. But Mediasite isn't helping
        WebElement presentationAddParent = driver.findElement(By.id("AddNewEntity_Element"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementById('AddNewEntity').style.display='inline-block';");
        WebElement presentationAddSelect = driver.findElement(By.id("AddNewEntity"));
        Select presentationAdd = new Select(presentationAddSelect);
        presentationAdd.selectByValue("PresentationAdd");
        driver.findElement(By.id("PresentationAdd")).click();

        return new MediasiteTemplatePage(driver);
    }


    public MediasiteFolderPage(WebDriver driver) {
        super(driver);
    }
}
