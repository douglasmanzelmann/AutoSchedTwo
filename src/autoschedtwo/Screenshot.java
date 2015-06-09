package autoschedtwo;

import org.openqa.selenium.WebDriver;

import java.awt.image.BufferedImage;

/**
 * Created by dmanzelmann on 6/9/2015.
 */
public interface Screenshot {
    void takeScreenshot(WebDriver driver);
    BufferedImage getStatusScreenshot();
}
