package autoschedtwo.portal;

import autoschedtwo.listing.Listing;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;
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
    //private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() / 2);
    private ExecutorService executor;

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
            /*Future<Listing> future = executor.submit(new Callable<Listing>() {
                @Override
                public Listing call() throws Exception {
                    try {
                        return callable.call();
                    } catch (Exception e) {
                        if (e.getMessage().contains("Error communicating with the remote browser. It may have died.")) {
                            System.out.println("CAUGHT EXCEPTION AND RECALLED");
                            return callable.call();
                        }

                        e.printStackTrace();
                        throw e;
                    }
                }
            });*/
            listingQueue.offer(future);
        }

        //executor.shutdown();
    }

    public PortalDriver(WebDriver driver, LinkedBlockingQueue listingQueue, ExecutorService executor) {
        super(driver);
        this.listingQueue = listingQueue;
        this.executor = executor;
    }

    public PortalDriver(WebDriver driver) {
        super(driver);
    }
}
