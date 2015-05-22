package autoschedtwo.portal;

import org.openqa.selenium.WebDriver;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public abstract class Portal {
    WebDriver driver;

    public Portal(WebDriver driver) {
        this.driver = driver;
    }
}
