package autoschedtwo.tms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class TMSLoginPage extends TMS {

    public TMSHomePage login(String username, String password) {
        driver.get("http://" + username + ":" + password + "@tms.rx.umaryland.edu/tms/");

        return PageFactory.initElements(driver, TMSHomePage.class);
    }


    public TMSLoginPage(WebDriver driver) {
        super(driver);
    }
}
