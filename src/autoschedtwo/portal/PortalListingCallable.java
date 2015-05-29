package autoschedtwo.portal;

import autoschedtwo.listing.Listing;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.Callable;

/**
 * Created by dmanzelmann on 5/29/2015.
 */
public class PortalListingCallable implements Callable<Listing>  {
    private ChromeOptions options;
    private WebDriver driver;
    private WebElement event;

    public PortalListingCallable(WebElement event) {
        System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
        options = new ChromeOptions();
        this.options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
        this.event = event;
    }

    public Listing call() {

    }
}
