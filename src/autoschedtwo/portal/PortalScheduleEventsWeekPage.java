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
public class PortalScheduleEventsWeekPage extends Portal implements Callable<Listing> {
    @FindBy(how = How.ID, using = "agenda")
    private static WebElement agenda;

    private static LinkedBlockingQueue<WebElement> eventItems;

    private class ListIterator implements Iterator<WebElement> {
        private int index = 0;
        List<WebElement> agendaItems = agenda.findElements(By.tagName("tr"));

        public boolean hasNext() {
            return index < agendaItems.size();
        }

        public void remove() { throw new UnsupportedOperationException(); }

        public WebElement next() {
            if (!hasNext()) throw new NoSuchElementException();
            WebElement webElement = agendaItems.get(index++);
            return webElement;
        }
    }

    public Iterator<WebElement> iterator() {
        return new ListIterator();
    }

    /*public List<Listing> getScheduleElements() {
        //List<PortalScheduleEventsEvent> eventsList = new ArrayList<>();
        List<Listing> newListings = new ArrayList<>();
        List<WebElement> agendaItems = agenda.findElements(By.tagName("tr"));

        int counter = 0;

        for (WebElement row : agendaItems) {
            newListings.add(ListingFactory.createListing(
                    new PortalScheduleEventsEvent(driver,
                            row.findElements(By.tagName("td"))).parse()));
        }
        /*for (WebElement row : agendaItems) {
            eventsList.add(new PortalScheduleEventsEvent(driver,
                    row.findElements(By.tagName("td"))).parse());
            System.out.println("new event: " + counter++);

        }

        //return eventsList;
        return newListings;
    }*/

    public LinkedBlockingQueue<WebElement> initEventsQueue() {
        eventItems = new LinkedBlockingQueue<>(agenda.findElements((By.tagName("tr"))));

        return eventItems;
    }

    public Listing call() {
        Listing newListing = ListingFactory.createListing(
                new PortalScheduleEventsEvent(driver, eventItems.poll().findElements(By.tagName("td"))).parse());
        System.out.println("new listing");

        return newListing;
    }

    public PortalScheduleEventsWeekPage() {

    }

    public PortalScheduleEventsWeekPage(WebDriver driver) {
        super(driver);
    }
}
