package autoschedtwo.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class PortalScheduleEventsWeekPage extends Portal {
    List<WebElement> agendaItems;

    @FindBy(how = How.ID, using = "agenda")
    private WebElement agenda;

    private class ListIterator implements Iterator<WebElement> {
        private int index = 0;

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

    public PortalScheduleEventsWeekPage(WebDriver driver) {
        super(driver);

        agendaItems = agenda.findElements(By.tagName("td"));
    }
}
