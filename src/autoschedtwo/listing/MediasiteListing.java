package autoschedtwo.listing;

import autoschedtwo.mediasite.*;
import autoschedtwo.portal.PortalScheduleEventsEvent;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class MediasiteListing extends Listing {
    private WebDriver driver;
    private ElementLocatorFactory factory;

    private String description;

    public MediasiteListing(PortalScheduleEventsEvent event) {
        super(event);
        driver = new ChromeDriver();
        factory = new AjaxElementLocatorFactory(driver, 30);

        setDescription(event.getClassDetails().split(";")[2].trim());
    }

    public void schedule(String username, String password, String tmsUsername, String tmsPassword) {
        driver.get("https://mediasite.umaryland.edu/Mediasite/Login?ReturnUrl=%2fmediasite%2fmanage");

        MediasiteLoginPage loginPage = PageFactory.initElements(driver, MediasiteLoginPage.class);
        MediasiteHomePage homePage = loginPage.login(username, password);
        PageFactory.initElements(factory, homePage);
        homePage.navigateToSchoolOfPharmacy();
        homePage.navigateToPharmD();


        MediasiteFolderPage folderPage = homePage.navigateToFolder(getSemesterFolder(getStartTime(), getClassPrefix()));
        PageFactory.initElements(factory, folderPage);

        MediasiteTemplatePage templatePage = folderPage.addNewPresentation();
        PageFactory.initElements(factory, templatePage);

        MediasiteNewPresentationPage newPresentationPage = templatePage.selectDefaultTemplate();
        PageFactory.initElements(factory, newPresentationPage);

        MediasiteNewPresentationReviewPage newPresentationReviewPage =
                newPresentationPage.createNewMediasitePresentation(getClassName() + getDateInMDYFormat(getStartTime()),
                        getDescription(), getDateInMDYFormat(getStartTime()), getHour(getStartTime()), getMinute(getStartTime()),
                        getTimeOfDay(getStartTime()), getFacultyAsQueue());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
