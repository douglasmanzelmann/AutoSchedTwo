package autoschedtwo.outlook;

import org.openqa.selenium.WebDriver;

/**
 * Created by dmanzelmann on 5/27/2015.
 */
public abstract class Outlook {
    WebDriver driver;

    public Outlook(WebDriver driver) {
        this.driver = driver;
    }
}
