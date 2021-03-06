package autoschedtwo.outlook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by dmanzelmann on 5/27/2015.
 */
public class OutlookLoginPage extends Outlook {
    @FindBy(how = How.ID, using = "username")
    WebElement username;

    @FindBy(how = How.ID, using = "password")
    WebElement password;

    @FindBy(how = How.XPATH, using = "//*[@id=\"tblMid\"]/tbody/tr[7]/td/table/tbody/tr[3]/td/input[1]")
    WebElement signIn;

    private void setUsername(String u) {
        username.sendKeys("rx\\" + u);
    }

    private void setPassword(String p) {
        password.sendKeys(p);
    }

    public OutlookHomePage login(String u, String p) {
        setUsername(u);
        setPassword(p);
        signIn.click();

        return PageFactory.initElements(driver, OutlookHomePage.class);
    }


    public OutlookLoginPage(WebDriver driver) {
        super(driver);
    }
}
