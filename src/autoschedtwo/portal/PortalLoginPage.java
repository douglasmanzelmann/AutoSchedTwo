package autoschedtwo.portal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class PortalLoginPage extends Portal {
    @FindBy(how = How.ID, using = "j_username")
    WebElement username;

    @FindBy(how = How.ID, using = "j_password")
    WebElement password;

    @FindBy(how = How.ID, using = "Login")
    WebElement login;

    public void setUsername(String u) {
        username.sendKeys(u);
    }

    public void setPassword(String p) {
        password.sendKeys(p);
    }

    public PortalScheduleEventsWeekPage login(String u, String p) {
        setUsername(u);
        setPassword(p);
        login.click();

        return PageFactory.initElements(driver, PortalScheduleEventsWeekPage.class);
    }

    public PortalLoginPage(WebDriver driver) {
        super(driver);
    }

}
