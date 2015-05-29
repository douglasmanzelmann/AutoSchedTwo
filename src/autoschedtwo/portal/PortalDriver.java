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
    private LinkedBlockingQueue<Listing> listingQueue;
    private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private CompletionService<Listing> completionService = new ExecutorCompletionService<Listing>(executor);
    private int numberOfEvents;

    public void getScheduleElements(String username, String password, int year, int month, int day) {
        driver.get("https://rxsecure.umaryland.edu/apps/schedules/view/?type=search&searchtype=resource&id=100&start=" +
                year + "-" + month + "-" + day + "&scope=week");
        PortalLoginPage loginPage = PageFactory.initElements(driver, PortalLoginPage.class);
        PortalScheduleEventsWeekPage scheduleEventsWeekPage = loginPage.login(username, password);
        webElementsQueue = scheduleEventsWeekPage.initEventsQueue();

        while (webElementsQueue.peek() != null) {
            Callable<Listing> callable = new PortalListingCallable(webElementsQueue.poll());
            Future<Listing> future = completionService.submit(callable);
        }


        /*Callable<Listing> callable = loginPage.login(username, password);
        Future<Listing> future = completionService.submit(callable);


        for (int i = 1; i < PortalScheduleEventsWeekPage.initEventsQueue(); i++) {
            callable = loginPage.login(username, password);
            future = completionService.submit(callable);
            System.out.println("added a future");
        }


        Future<Listing> completedFuture;
        Listing newListing;
        while (numberOfEvents > 0) {
            try {
                completedFuture = completionService.take();
                numberOfEvents--;
                newListing = completedFuture.get();
                listingQueue.offer(newListing);
                System.out.println(newListing);
            } catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }
        }  */


        //return scheduleEventsWeekPage.getScheduleElements();
    }

    public PortalDriver(WebDriver driver, LinkedBlockingQueue<Listing> listingsQueue) {
        super(driver);
        this.listingQueue = listingsQueue;
    }

    public PortalDriver(WebDriver driver) {
        super(driver);
    }
}
