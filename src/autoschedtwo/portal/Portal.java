package autoschedtwo.portal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public abstract class Portal {
    ChromeOptions options;
    WebDriver driver;

    public Portal() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\test\\Desktop\\chromedriver.exe");
        options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
    }

    public Portal(WebDriver driver) {
        this.driver = driver;
    }
}
