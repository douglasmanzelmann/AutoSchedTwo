package autoschedtwo.mediasite;
import autoschedtwo.mediasite.Mediasite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by dmanzelmann on 5/21/2015.
 */
public class MediasiteAddExistingPresentersPage extends Mediasite {
    WebDriverWait wait;

    @FindBy(how = How.ID, using = "SearchTerm")
    WebElement searchTerm;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Search")
    WebElement searchButton;

    @FindBy(how = How.ID, using = "AddSelected")
    WebElement addSelected;

    @FindBy(how = How.ID, using = "TemplatedListNoResults")
    WebElement noResults;

    @FindBy(how = How.ID, using = "SelectorCancel")
    WebElement cancel;

    public void enterSearchTerm(String term) {
        searchTerm.clear();
        searchTerm.sendKeys(term);
        //new Actions(driver).moveToElement(searchTerm).sendKeys(term).build().perform();
    }

    public void clickSearch() {
        //searchButton.click();
        new Actions(driver).moveToElement(searchButton).click().build().perform();
    }

    public boolean selectFirstOption() {
        if (noResults.isDisplayed()) {
            return false;
        }

        List<WebElement> results = driver.findElements(By.className("ResultsTable"));
        WebElement firstResult = results.get(0);
        firstResult.findElement(By.id("Check")).click();
        return true;
    }

    public void clickAddSelected() {
        addSelected.click();
    }

    public void clickCancel() {
        cancel.click();
    }

    public Queue<String> addExistingPresenters(Queue<String> presenters) {
        Queue<String> presentersWhoDoNotExist = new LinkedList<>();

        while (presenters.peek() != null) {
            try {
                String presenter = presenters.poll();

                //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchTerm")));
                enterSearchTerm(presenter);
                clickSearch();
                // still need to find a better solution
                Thread.sleep(5000);

                if (!selectFirstOption()) {
                    presentersWhoDoNotExist.add(presenter);
                }
            } catch (InterruptedException e) { e.printStackTrace(); }
        }


        clickAddSelected();

        return presentersWhoDoNotExist;
    }


    public MediasiteAddExistingPresentersPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 30);
    }
}
