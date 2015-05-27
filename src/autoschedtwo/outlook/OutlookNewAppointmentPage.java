package autoschedtwo.outlook;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by dmanzelmann on 5/27/2015.
 */
public class OutlookNewAppointmentPage extends Outlook {
    @FindBy(how = How.ID, using = "txtSubj")
    WebElement subject;

    @FindBy(how = How.ID, using = "txtLoc")
    WebElement location;

    @FindBy(how = How.XPATH, using = "html/body/div[15]/div[2]/div[1]/div[1]/div[7]/div[3]/div/div/div/div/span/input")
    WebElement startTime;

    @FindBy(how = How.XPATH, using = "html/body/div[15]/div[2]/div[1]/div[1]/div[8]/div[3]/div/div/div/div/span/input")
    WebElement endTime;

    @FindBy(how = How.ID, using = "cat")
    WebElement category;

    @FindBy(how = How.ID, using = "saveclose")
    WebElement save;

    private void setSubject(String s) {
        subject.sendKeys(s);
    }

    private void setLocation(String l) {
        location.sendKeys(l);
    }

    private void setStartTime(String st) {
        startTime.clear();
        startTime.sendKeys(st);
    }

    private void setEndTime(String et) {
        endTime.clear();
        endTime.sendKeys(et);
    }

    private void setCategory(String c) {
        new Actions(driver).moveToElement(category).click().build().perform();
        //JavascriptExecutor js = (JavascriptExecutor)driver;
        //js.executeScript("document.getElementById('divCatM').style.display='inline-block';");

        // Hard Coded value.
        WebElement mediasiteCat = driver.findElement(By.xpath("//*[@cmd='cat:Mediasite']"));
        new Actions(driver).moveToElement(mediasiteCat).click().build().perform();

    }

    private void setSave() {
        save.click();
        save.click();
    }

    public void schedule(String s, String l, String st, String et, String c) {
        setSubject(s);
        setLocation(l);
        setStartTime(st);
        setEndTime(et);
        setCategory(c);
        setSave();
    }

    public OutlookNewAppointmentPage(WebDriver driver) {
        super(driver);
    }
}
