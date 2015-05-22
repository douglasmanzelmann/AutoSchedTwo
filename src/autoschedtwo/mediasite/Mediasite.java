package autoschedtwo.mediasite;

import org.openqa.selenium.WebDriver;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public abstract class Mediasite {
    WebDriver driver;

    public Mediasite(WebDriver driver) {
        this.driver = driver;
    }
}
