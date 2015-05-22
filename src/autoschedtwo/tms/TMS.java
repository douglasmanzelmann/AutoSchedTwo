package autoschedtwo.tms;

import org.openqa.selenium.WebDriver;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public abstract class TMS {
    WebDriver driver;

    public TMS(WebDriver driver) {
        this.driver = driver;
    }
}
