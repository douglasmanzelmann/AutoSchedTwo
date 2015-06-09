package autoschedtwo.listing;

import autoschedtwo.Login;
import autoschedtwo.Scheduler;
import autoschedtwo.Screenshot;
import autoschedtwo.mediasite.*;
import autoschedtwo.portal.PortalScheduleEventsEvent;
import org.joda.time.DateTime;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class MediasiteListing extends Listing implements Screenshot {
    private ChromeOptions options;
    private WebDriver driver;
    private ElementLocatorFactory factory;
    private Login login;
    private File scrFile;

    private final String description;

    public MediasiteListing(PortalScheduleEventsEvent event) {
        super(event);
        description = event.getClassDetails().split(";")[2].trim();
        setActivity("Mediasite");
        setNeedsToBeScheduled(true);
    }

    /*public Listing call() {
        schedule(login.getUsername(), login.getPassword());

        return this;
    }*/

    public void setLogin(Login login) {
        this.login = login;
    }

    public MediasiteListing schedule(String username, String password) {
        options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver();
        factory = new AjaxElementLocatorFactory(driver, 30);
        driver.get("https://mediasite.umaryland.edu/Mediasite/Login?ReturnUrl=%2fmediasite%2fmanage");

        try {
            MediasiteLoginPage loginPage = PageFactory.initElements(driver, MediasiteLoginPage.class);
            MediasiteHomePage homePage = loginPage.login(username, password);
            PageFactory.initElements(factory, homePage);
            homePage.navigateToSchoolOfPharmacy();
            //homePage.navigateToPharmD();
            homePage.navigateToTraining();
            homePage.navigateToTesting();

            //MediasiteFolderPage folderPage = homePage.navigateToFolder(getSemesterFolder(getStartTime(), getClassPrefix()));
            //PageFactory.initElements(factory, folderPage);
            MediasiteFolderPage folderPage = homePage.navigateToNowhere();
            PageFactory.initElements(factory, folderPage);

            MediasiteTemplatePage templatePage = folderPage.addNewPresentation();
            PageFactory.initElements(factory, templatePage);

            MediasiteNewPresentationPage newPresentationPage = templatePage.selectDefaultTemplate();
            PageFactory.initElements(factory, newPresentationPage);

            MediasiteNewPresentationReviewPage newPresentationReviewPage =
                    newPresentationPage.createNewMediasitePresentation(getClassName() + " " + getDateInMDYFormat(getStartTime()),
                            getDescription(), getDateInMDYFormat(getStartTime()), getHour(getStartTime()), getMinute(getStartTime()),
                            getTimeOfDay(getStartTime()), getFacultyAsQueue());

        } catch (Exception e) {
            setStatus("failed");
            e.printStackTrace();
        }

        setStatus("finished");
        takeScreenshot(driver);
        driver.close();
        return this;
    }

    public String getDescription() {
        return description;
    }

    private Queue<String> getFacultyAsQueue() {
        Queue<String> facultyQueue = new LinkedList<>();

        for (String s : getFaculty())
            facultyQueue.add(s);

        return facultyQueue;
    }

    private static String getSemesterFolder(DateTime date, String classPrefix) {
        String semesterAbbreviation = "";
        if (date.getMonthOfYear() >= 1 && date.getMonthOfYear() < 6)
            semesterAbbreviation = "SP";
        else if (date.getMonthOfYear() >= 8 && date.getMonthOfYear() <= 12)
            semesterAbbreviation = "FA";

        String yearAbbreviation = Integer.toString(date.getYear() % 100);

        return semesterAbbreviation + yearAbbreviation + classPrefix;
    }

    /*public String toString() {
        return "Scheduled: " + scheduled + " " + super.toString();
    }*/

    public void takeScreenshot(WebDriver driver) {
        scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    }

    public BufferedImage getStatusScreenshot() {
        try {
            return ImageIO.read(scrFile);
        }   catch (IOException e) { e.printStackTrace(); }


        return null;
    }

    public static void main(String[] args) {
        String temp = "PHAR535 Pharmaceutics Lectures \n" +
                "\n" +
                "28: Biologics 1 (Available in Mediasite- Watch by the end of this week) \n" +
                "\n" +
                " Online Activity";
        List<String> list = Arrays.asList(temp.split("\n"));
        System.out.println(list.get(2));

    }

}
