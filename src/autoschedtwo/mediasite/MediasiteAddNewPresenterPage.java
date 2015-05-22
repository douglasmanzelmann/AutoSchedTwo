package autoschedtwo.mediasite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Queue;

/**
 * Created by dmanzelmann on 5/21/2015.
 */
public class MediasiteAddNewPresenterPage extends Mediasite {
    @FindBy(how = How.ID, using = "LastName")
    WebElement lastName;

    @FindBy(how = How.ID, using = "AdditionalInfo")
    WebElement firstName;

    @FindBy(how = How.ID, using = "Save")
    WebElement save;

    public void enterLastName(String name) {
        lastName.sendKeys(name);
    }

    public void enterFirstName(String name) {
        firstName.sendKeys(name);
    }

    public void save() {
        WebElement addNewDialog = driver.findElement(By.id("DialogPresenterSelector"));
        addNewDialog.findElement(By.id("Save")).click();
    }

    public void addNewPresenter(String lastName, String firstName) {
        enterLastName(lastName);
        enterFirstName(firstName);
        save();
    }

    public MediasiteAddNewPresenterPage(WebDriver driver) {
        super(driver);
    }
}
