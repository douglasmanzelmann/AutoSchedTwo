package autoschedtwo.portal;

import autoschedtwo.listing.Listing;
import autoschedtwo.listing.ListingFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class PortalScheduleEventsWeekPage extends Portal {
    @FindBy(how = How.ID, using = "agenda")
    WebElement agenda;

    private static LinkedBlockingQueue<WebElement> eventItems;


    public LinkedBlockingQueue<WebElement> initEventsQueue() {
        eventItems = new LinkedBlockingQueue<>(agenda.findElements((By.tagName("tr"))));

        return eventItems;
    }

    public PortalScheduleEventsWeekPage(WebDriver driver) {
        super(driver);
    }
}
