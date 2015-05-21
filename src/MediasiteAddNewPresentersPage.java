import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Queue;

/**
 * Created by dmanzelmann on 5/21/2015.
 */
public class MediasiteAddNewPresentersPage {
    WebDriver driver;

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
        save.click();
    }

    public void addNewPresenter(String lastName, String firstName) {
        enterLastName(lastName);
        enterFirstName(firstName);
        save();
    }

    public MediasiteAddNewPresentersPage(WebDriver driver) {
        this.driver = driver;
    }
}
