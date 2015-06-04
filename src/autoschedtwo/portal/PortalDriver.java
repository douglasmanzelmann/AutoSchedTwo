package autoschedtwo.portal;

import autoschedtwo.listing.Listing;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class PortalDriver extends Portal {
    private LinkedBlockingQueue<WebElement> webElementsQueue;
    private LinkedBlockingQueue<Future<Listing>> listingQueue;
    private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private boolean shutDown;

    public void getScheduleElements(String username, String password, int year, int month, int day) {
        driver.get("https://rxsecure.umaryland.edu/apps/schedules/view/?type=search&searchtype=resource&id=100&start=" +
                year + "-" + month + "-" + day + "&scope=week");
        PortalLoginPage loginPage = PageFactory.initElements(driver, PortalLoginPage.class);
        PortalScheduleEventsWeekPage scheduleEventsWeekPage = loginPage.login(username, password);
        webElementsQueue = scheduleEventsWeekPage.initEventsQueue();

        // doesn't need to be a queue...
        // this is sequential....
        // could do a parallel stream
        while (webElementsQueue.peek() != null) {
            Callable<Listing> callable = new PortalListingCallable(webElementsQueue.poll());
            Future<Listing> future = executor.submit(callable);
            listingQueue.add(future);
        }

        executor.shutdown();
        shutDown = true;
    }

    public boolean isShutDown() {
        return shutDown;
    }

    public PortalDriver(WebDriver driver, LinkedBlockingQueue<Future<Listing>> listingsQueue) {
        super(driver);
        this.listingQueue = listingsQueue;
        shutDown = false;
    }

    public PortalDriver(WebDriver driver) {
        super(driver);
    }
}
