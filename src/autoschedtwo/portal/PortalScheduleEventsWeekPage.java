package autoschedtwo.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class PortalScheduleEventsWeekPage extends Portal {
    @FindBy(how = How.ID, using = "agenda")
    private WebElement agenda;

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

    public ArrayList<ArrayList<WebElement>> getScheduleElements() {
        ArrayList<ArrayList<WebElement>> elementMatrix = new ArrayList<ArrayList<WebElement>>();
        List<WebElement> agendaItems = agenda.findElements(By.tagName("tr"));

        for (WebElement row : agendaItems) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            ArrayList<WebElement> rowList = new ArrayList<>();
            for (WebElement column : columns) {
                rowList.add(column);
            }
            elementMatrix.add(rowList);
        }

        return elementMatrix;
    }

    public Iterator<WebElement> iterator() {
        return new ListIterator();
    }

    public PortalScheduleEventsWeekPage(WebDriver driver) {
        super(driver);
    }
}
