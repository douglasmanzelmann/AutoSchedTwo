import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by dmanzelmann on 5/21/2015.
 */
public class MediasiteNewPresentationPage {
    WebDriver driver;

    @FindBy(how = How.ID,  using = "Title")
    WebElement title;

    @FindBy(how = How.ID,  using = "Description")
    WebElement description;

    @FindBy(how = How.ID, using = "RecordDate")
    WebElement date;

    @FindBy(how = How.ID, using = "Hour")
    WebElement hour;

    @FindBy(how = How.ID, using = "Minute")
    WebElement minute;

    @FindBy(how = How.ID, using = "AmPm")
    WebElement timeOfDay;

    @FindBy(how = How.ID, using = "Save")
    WebElement save;

    @FindBy(how = How.ID, using = "AddPresenter")
    WebElement addPresenter;

    public void setTitle(String t) {
        title.sendKeys(t);
    }

    public void setDescription(String d) {
        description.sendKeys(d);
    }

    public void setRecordDate(String d) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].value = '" + d + "';", date);
    }

    public void setHour(String h) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].value = '" + h + "';", hour);
    }

    public void setMinute(String m) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].value = '" + m + "';", minute);
    }

    public void setTimeOfDay(String t) {
        Select timeOfDaySelect = new Select(timeOfDay);
        if (t.equals("AM"))
            timeOfDaySelect.selectByValue("AM");
        else if (t.equals("PM"))
            timeOfDaySelect.selectByValue("PM");
    }

    public void removeDefaultPresenter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("var list = document.getElementById('selectedPresentersList'); list.innerHTML = '';");

    }

    public Queue<String> setExistingPresenters(Queue<String> presenters) {
        Queue<String> newPresenters = new LinkedList<>();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('AddPresenter').style.display='block';");

        Select addPresenterSelect = new Select(addPresenter);
        addPresenterSelect.selectByValue("AddExisting");
        addPresenter.click();

        MediasiteAddExistingPresentersPage existingPresentersPage =
                PageFactory.initElements(driver, MediasiteAddExistingPresentersPage.class);

        newPresenters = existingPresentersPage.addExistingPresenters(presenters);

        return newPresenters;
    }

    public void setNewPresenters(Queue<String> presenters) {
        Select addPresenterSelect = new Select(addPresenter);

        while (presenters.peek() != null) {
            String presenter = presenters.poll();
            addPresenterSelect.selectByValue("AddNew");
            MediasiteAddNewPresentersPage newPresentersPage =
                    PageFactory.initElements(driver, MediasiteAddNewPresentersPage.class);

            String[] fullName = presenter.split(",");
            newPresentersPage.addNewPresenter(fullName[0], fullName[1].trim());

        }


    }

    public MediasiteNewPresentationReviewPage save() {
        return PageFactory.initElements(driver, MediasiteNewPresentationReviewPage.class);
    }


    public void createNewMediasitePresentation(String t, String d,
                                                                             String r, String h,
                                                                             String m, String tod,
                                                                             Queue<String> p) {
        setTitle(t);
        setDescription(d);
        setRecordDate(r);
        setHour(h);
        setMinute(m);
        setTimeOfDay(tod);
        removeDefaultPresenter();

        Queue<String> newPresenters = setExistingPresenters(p);

        if (newPresenters.size() > 0)
            setNewPresenters(newPresenters);


    }

    public MediasiteNewPresentationPage(WebDriver driver) {
        this.driver = driver;
    }
}
